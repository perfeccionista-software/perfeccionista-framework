package io.perfeccionista.framework;

import io.perfeccionista.framework.exceptions.EnvironmentNotInitialized;
import io.perfeccionista.framework.exceptions.ServiceNotFound;
import io.perfeccionista.framework.logging.Logger;
import io.perfeccionista.framework.logging.LoggerFactory;
import io.perfeccionista.framework.service.ConfiguredServiceHolder;
import io.perfeccionista.framework.utils.EnvironmentLogger;
import org.jetbrains.annotations.NotNull;
import io.perfeccionista.framework.exceptions.RegisterDuplicate;
import io.perfeccionista.framework.service.Service;
import io.perfeccionista.framework.service.ServiceConfiguration;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.ENVIRONMENT_NOT_INITIALIZED;
import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.ENVIRONMENT_SERVICE_INITIALIZING_FAILED;
import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.SERVICE_NOT_FOUND;
import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.SERVICE_REGISTER_BY_CLASS_DUPLICATE;
import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.SERVICE_REGISTER_CLASS_CAST;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.newInstance;

/**
 * Экземпляры {@link Environment} инкапсулируют в себе среду выполнения
 * теста. Среда выполнения теста содержит тестовый класс, который запускается
 * в текущем потоке а так же набор {@link Service}.
 * {@link Service} регистрируются внутри {@link Environment} по классу.
 * {@link Environment} может содержать только один экземпляр {@link Service},
 * зарегистрированный по конкретному классу.
 *
 * Аннотация {@link UseEnvironment} указывает конфигурацию,
 * которую использует экземпляр {@link Environment}.
 * Конфигурация {@link EnvironmentConfiguration} должна иметь конструктор по умолчанию.
 *
 * Можно привязать текущий экземпляр {@link Environment} к потоку выполнения
 * теста через метод {@link Environment#setEnvironmentForCurrentThread()}
 * Чтобы получить этот экзепляр в этом же потоке, необходимо использовать статический
 * метод {@link Environment#get()}
 *
 * Этот класс представляет собой базовый функционал, который используется в
 * {@link Environment}. Пользователи могут самостоятельно расширять этот класс
 * и использовать его в своем проекте.
 * Пользовательская реализация класса указывается в конфигурации {@link EnvironmentConfiguration}
 * Наследники этого класса долджны иметь доступный конструктор с аргументами:
 * {@code (Class<?> testClass, EnvironmentConfiguration configuration}
 */
public class Environment {
    private static final Logger logger = LoggerFactory.getLogger(Environment.class);

    protected static final ThreadLocal<Environment> INSTANCES = new ThreadLocal<>();

    protected final EnvironmentConfiguration configuration;
    protected final Map<Class<? extends Service>, Service> services = new HashMap<>();

    /**
     * Экземпляр {@link Environment} создается во время инициализации
     * Наследники этого класса должны иметь такой же конструктор
     */
    public Environment(@NotNull Class<? extends EnvironmentConfiguration> configurationClass) {
        this(newInstance(configurationClass));
    }

    /**
     * Экземпляр {@link Environment} создается во время инициализации
     * Наследники этого класса должны иметь такой же конструктор
     */
    public Environment(@NotNull EnvironmentConfiguration configuration) {
        this.configuration = configuration;
        this.configuration.getLoggerClass()
                .ifPresent(LoggerFactory::setLogger);
        logger.config(() -> "Environment configuration check");
        checkEnvironmentConfiguration(this.configuration);
        logger.config(() -> "Environment configuration check success");
    }

    public Environment init() {
        logger.debug(() -> "Environment configuration initialization");
        initEnvironment(configuration);
        this.getServices().forEach(Service::beforeTest);
        logger.debug(() -> "Environment configuration initialization success");
        return this;
    }

    public void shutdown() {
        logger.debug(() -> "Environment shutdown");
        this.getServices().forEach(Service::afterTest);
        logger.debug(() -> "Environment shutdown success");
    }

    /**
     * Регистрируем все экземпляры {@link Service}, которые описаны в
     * конфигурации внутри созданного {@link Environment}.
     * Регистрируемый {@link Service} обязательно должен быть
     * экземпляром класса по которому он регистрируется.
     * @param service регистрируемый экземпляр {@link Service}
     * @return текущий экземпляр {@link Environment}
     */
    public Environment register(@NotNull Class<? extends Service> serviceClass, @NotNull Service service) {
        if (!serviceClass.isInstance(service)) {
            throw new ClassCastException(
                    SERVICE_REGISTER_CLASS_CAST.getMessage(service, serviceClass));
        }
        if (services.containsKey(serviceClass)) {
            throw RegisterDuplicate.exception(SERVICE_REGISTER_BY_CLASS_DUPLICATE.getMessage(service));
        }
        services.put(serviceClass, service);
        return this;
    }

    /**
     * Возвращает экземпляр зарегистрированного {@link Service}
     * по строгому соответствию класса по которому этот {@link Service}
     * был зарегистрирован.
     * @param serviceClass класс, по которому {@link Service} зарегистрирован
     * @param <T>          тип возвращаемого {@link Service}
     * @return Экземпляр сервиса, который был зарегистрирован
     * по указанному классу или ServiceNotFoundException, если по указанному классу
     * не зарегистрировано экземпляров {@link Service}.
     */
    public @NotNull <T extends Service> T getService(@NotNull Class<T> serviceClass) {
        if (services.containsKey(serviceClass)) {
            return serviceClass.cast(services.get(serviceClass));
        }
        throw ServiceNotFound.exception(SERVICE_NOT_FOUND.getMessage(serviceClass.getCanonicalName()));
    }

    public @NotNull <T extends Service> Optional<T> getOptionalService(@NotNull Class<T> serviceClass) {
        if (services.containsKey(serviceClass)) {
            return Optional.of(serviceClass.cast(services.get(serviceClass)));
        }
        return Optional.empty();
    }

    /**
     * набор зарегистрированных экземпляров {@link Service}
     * @return {@link Stream}
     */
    public Stream<Service> getServices() {
        return services.values().stream();
    }

    /**
     * Возвращает классы всех зарегистрированных {@link Service}
     */
    public Set<Class<? extends Service>> getServiceClasses() {
        return services.keySet();
    }

    /**
     * @return Возвращает используемую {@link Environment} конфигурацию
     */
    public @NotNull EnvironmentConfiguration getEnvironmentConfiguration() {
        return configuration;
    }

    // ThreadLocal access point

    /**
     * @return {@link Optional} который содержит экземпляр
     * {@link Environment} для текущего {@link Thread}, если экземпляр был
     * привязан к нему ранее.
     * В противном случае, возвращает {@link Optional#empty()}
     */
    public static Optional<Environment> get() {
        return Optional.ofNullable(INSTANCES.get());
    }

    /**
     * {@link Environment} для текущего {@link Thread}.
     * В противном случае, выбрасывает исключение {@link io.perfeccionista.framework.exceptions.EnvironmentNotInitialized.EnvironmentNotInitializedException}
     */
    public static Environment getCurrent() {
        return get().orElseThrow(() -> EnvironmentNotInitialized.exception(ENVIRONMENT_NOT_INITIALIZED.getMessage()));
    }

    /**
     * Привязывает этот экземпляр {@link Environment} к потоку,
     * в котором он создан {@link Thread}
     */
    public Environment setEnvironmentForCurrentThread() {
        INSTANCES.set(this);
        return this;
    }

    /**
     * Отвязывает этот экземпляр {@link Environment} от потока,
     * в котором он создан {@link Thread}
     */
    public Environment removeEnvironmentForCurrentThread() {
        INSTANCES.remove();
        return this;
    }

    // Check and Initialization

    /**
     * TODO: На этот метод нужны тесты
     * Этот метод выполняет проверки валидности конфигурации для инициализации {@link Environment}
     */
    protected void checkEnvironmentConfiguration(@NotNull EnvironmentConfiguration environmentConfiguration) {
        // TODO: Добавить необходимые проверки валидности
    }

    /**
     * Этот метод отвечает за инициализацию всех провайдеров, описанных в конфигурации
     * @param environmentConfiguration конфигурация
     */
    protected void initEnvironment(@NotNull EnvironmentConfiguration environmentConfiguration) {
        EnvironmentLogger environmentLogger = EnvironmentLogger.of(environmentConfiguration);
        environmentLogger.start();

        environmentConfiguration.getServices()
                .entrySet().stream()
                .sorted(Comparator.comparingInt(entry -> entry.getValue().getOrder()))
                .forEachOrdered(entry -> {
                    long startTime = System.nanoTime();
                    Class<? extends Service> serviceClass = entry.getKey();
                    ConfiguredServiceHolder serviceHolder = entry.getValue();
                    if (serviceHolder.isEnabled()) {
                        Class<? extends ServiceConfiguration> serviceConfigurationClass = serviceHolder.getServiceConfigurationClass();
                        if (Objects.isNull(serviceConfigurationClass)) {
                            initService(serviceClass);
                        } else {
                            ServiceConfiguration serviceConfiguration = newInstance(serviceConfigurationClass);
                            initService(serviceClass, serviceConfiguration);
                        }
                    }
                    environmentLogger.addServiceRecord(entry.getValue(), startTime, System.nanoTime());
                });

        environmentLogger.finish();
        logger.info(environmentLogger::toString);
    }

    protected void initService(@NotNull Class<? extends Service> serviceClass) {
        Service serviceInstance = newInstance(serviceClass);
        serviceInstance.init(this, null);
        register(serviceClass, serviceInstance);
    }

    protected void initService(@NotNull Class<? extends Service> serviceClass,
                               @NotNull ServiceConfiguration serviceConfiguration) {
        try {
            Optional<Class<? extends Service>> customImplementation = serviceConfiguration.getImplementation();
            // TODO: Проверить соответствие провайдера и конфигурации и привести их к <T>
            Class<? extends Service> serviceImplementation = customImplementation.orElse(serviceClass);
            Service serviceInstance = newInstance(serviceImplementation);
            serviceInstance.init(this, serviceConfiguration);
            register(serviceClass, serviceInstance);
        } catch (Exception ex) {
           throw EnvironmentNotInitialized.exception(
                   ENVIRONMENT_SERVICE_INITIALIZING_FAILED.getMessage(serviceClass.getCanonicalName()),
                   ex
           );
        }
    }

}
