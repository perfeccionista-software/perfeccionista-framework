package io.perfeccionista.framework;

import io.perfeccionista.framework.exceptions.EnvironmentAlreadyInitialized;
import io.perfeccionista.framework.exceptions.EnvironmentNotInitialized;
import io.perfeccionista.framework.exceptions.ServiceNotFound;
import io.perfeccionista.framework.exceptions.attachments.TextAttachmentEntry;
import io.perfeccionista.framework.preconditions.Preconditions;
import io.perfeccionista.framework.service.ConfiguredServiceHolder;
import io.perfeccionista.framework.utils.EnvironmentLogger;
import io.perfeccionista.framework.exceptions.RegisterDuplicate;
import io.perfeccionista.framework.service.Service;
import io.perfeccionista.framework.service.ServiceConfiguration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.ENVIRONMENT_ALREADY_INITIALIZED;
import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.ENVIRONMENT_NOT_INITIALIZED;
import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.ENVIRONMENT_SERVICE_INITIALIZING_FAILED;
import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.SERVICE_NOT_FOUND;
import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.SERVICE_REGISTER_BY_CLASS_DUPLICATE;
import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.SERVICE_REGISTER_CLASS_CAST;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.newInstance;

/**
 * Экземпляры {@link Environment} инкапсулируют в себе среду выполнения
 * теста. Среда выполнения теста содержит тестовый класс, который запускается
 * в текущем потоке. А так же набор {@link Service}.
 * {@link Service} регистрируются внутри {@link Environment} по классу.
 * {@link Environment} может содержать только один экземпляр {@link Service},
 * зарегистрированный по конкретному классу.
 *
 * Аннотация {@link SetEnvironmentConfiguration} указывает конфигурацию,
 * которую использует экземпляр {@link Environment}.
 * Конфигурация {@link EnvironmentConfiguration} должна иметь конструктор по умолчанию.
 *
 * Можно привязать текущий экземпляр {@link Environment} к потоку выполнения
 * теста через метод {@link Environment#setForCurrentThread(Environment)}}
 * Чтобы получить этот экземпляр в этом же потоке, необходимо использовать статический
 * метод {@link Environment#getForCurrentThread()} }
 *
 * Этот класс представляет собой базовый функционал, который используется в
 * {@link Environment}. Пользователи могут самостоятельно расширять этот класс
 * и использовать его в своем проекте.
 * Пользовательская реализация класса указывается в конфигурации {@link EnvironmentConfiguration}
 * Наследники этого класса должны иметь доступный конструктор с аргументами:
 * {@code (Class<?> testClass, EnvironmentConfiguration configuration}
 */
public class Environment {
    private static final Logger logger = LoggerFactory.getLogger(Environment.class);

    public static final String PERFECCIONISTA_PROPERTIES_FILE = "perfeccionista.properties";
    public static final String ENVIRONMENT_ATTACHMENT_NAME = "Environment configuration";

    protected static final InheritableThreadLocal<Environment> INSTANCES = new InheritableThreadLocal<>();
    protected static Deque<Runnable> afterAllHooks = new ArrayDeque<>();

    protected final String testName;
    protected final EnvironmentConfiguration configuration;
    protected final Map<String, Object> relatedObjects = new HashMap<>();
    protected final Map<Class<? extends Service>, Service> services = new HashMap<>();

    protected TextAttachmentEntry environmentAttachment = TextAttachmentEntry.of(ENVIRONMENT_ATTACHMENT_NAME, "Environment is not initialized");

    public Environment(@NotNull Class<? extends EnvironmentConfiguration> configurationClass,
                       @Nullable String testName) {
        this(newInstance(configurationClass), testName);
    }

    /**
     * Экземпляр {@link Environment} создается во время инициализации
     * Наследники этого класса должны иметь такой же конструктор
     */
    public Environment(@NotNull Class<? extends EnvironmentConfiguration> configurationClass) {
        this(newInstance(configurationClass), null);
    }

    /**
     * Экземпляр {@link Environment} создается во время инициализации
     * Наследники этого класса должны иметь такой же конструктор
     */
    public Environment(@NotNull EnvironmentConfiguration configuration,
                       @Nullable String testName) {
        Preconditions.notNull(configuration, "configuration must not be null");
        this.testName = testName;
        this.configuration = configuration;
    }

    public Environment(@NotNull EnvironmentConfiguration configuration) {
        this(configuration, null);
    }

    public Environment init() {
        if (!this.services.isEmpty()) {
            throw EnvironmentAlreadyInitialized.exception(ENVIRONMENT_ALREADY_INITIALIZED.getMessage());
        }
        logger.debug("Environment configuration initialization [" + this + "]");
        initEnvironment(configuration, testName);
        logger.debug("Environment configuration initialization success [" + this + "]");
        return this;
    }

    public Environment beforeTest() {
        logger.debug("Environment configuration before test task [" + this + "]");
        this.getServices().forEach(Service::beforeTest);
        logger.debug("Environment configuration before test task success [" + this + "]");
        return this;
    }

    public Environment afterTest() {
        logger.debug("Environment configuration after test task [" + this + "]");
        this.getServices().forEach(Service::afterTest);
        logger.debug("Environment configuration after test task success [" + this + "]");
        return this;
    }

    public Environment shutdown() {
        logger.debug("Environment shutdown [" + this + "]");
        this.services.clear();
        logger.debug("Environment shutdown success [" + this + "]");
        return this;
    }

    /**
     * Используется для передачи в окружение теста информации о внешних объектах
     * по отношению к тесту. Например, это может быть TestContext или ExtensionContext,
     * если экземпляр Environment создается через Extension или другую сущность,
     * внешнюю, по отношению к тесту.
     * Эта информация может быть использована при инициализации сервисов.
     * Добавление объектов для их использования при инициализации сервисов должно происходить
     * ДО вызова метода Environment.init().
     * @param relatedObject
     * @return
     */
    public Environment addRelatedObject(@NotNull String name, @Nullable Object relatedObject) {
        this.relatedObjects.put(name, relatedObject);
        return this;
    }

    public Map<String, Object> getRelatedObjects() {
        return Collections.unmodifiableMap(relatedObjects);
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
        Preconditions.notNull(serviceClass, "serviceClass must not be null");
        Preconditions.notNull(service, "service must not be null");
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
        Preconditions.notNull(serviceClass, "serviceClass must not be null");
        if (services.containsKey(serviceClass)) {
            return serviceClass.cast(services.get(serviceClass));
        }
        throw ServiceNotFound.exception(SERVICE_NOT_FOUND.getMessage(serviceClass.getCanonicalName()));
    }

    public @NotNull <T extends Service> Optional<T> getOptionalService(@NotNull Class<T> serviceClass) {
        Preconditions.notNull(serviceClass, "serviceClass must not be null");
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

    /**
     * @return Возвращает текстовое представление текущей инициализированной конфигурации.
     * Или пустой аттачмент, если на момент запроса Environment еще не был инициализирован
     */
    public @NotNull TextAttachmentEntry getEnvironmentAttachment() {
        return this.environmentAttachment;
    }

    // ThreadLocal

    public static Environment createForCurrentThread(@NotNull Class<? extends EnvironmentConfiguration> configurationClass,
                                                     @Nullable String testName) {
        Preconditions.notNull(configurationClass, "Environment configuration class must not be null");
        Environment environment = new Environment(configurationClass, testName);
        INSTANCES.set(environment);
        return environment;
    }

    /**
     *
     * @param configurationClass
     * @return
     */
    public static Environment createForCurrentThread(@NotNull Class<? extends EnvironmentConfiguration> configurationClass) {
        Preconditions.notNull(configurationClass, "Environment configuration class must not be null");
        Environment environment = new Environment(configurationClass);
        INSTANCES.set(environment);
        return environment;
    }

    public static Environment createForCurrentThread(@NotNull EnvironmentConfiguration configuration,
                                                     @Nullable String testName) {
        Preconditions.notNull(configuration, "Environment configuration instance must not be null");
        Environment environment = new Environment(configuration, testName);
        INSTANCES.set(environment);
        return environment;
    }

    /**
     *
     * @param configuration
     * @return
     */
    public static Environment createForCurrentThread(@NotNull EnvironmentConfiguration configuration) {
        Preconditions.notNull(configuration, "Environment configuration instance must not be null");
        Environment environment = new Environment(configuration);
        INSTANCES.set(environment);
        return environment;
    }

    /**
     */
    public static boolean existForCurrentThread() {
        return Objects.nonNull(INSTANCES.get());
    }

    /**
     * {@link Environment} для текущего {@link Thread}.
     * В противном случае, выбрасывает исключение {@link io.perfeccionista.framework.exceptions.EnvironmentNotInitialized}
     */
    public static Environment getForCurrentThread() {
        return Optional.ofNullable(INSTANCES.get())
                .orElseThrow(() -> EnvironmentNotInitialized.exception(ENVIRONMENT_NOT_INITIALIZED.getMessage()));
    }

    /**
     * Привязывает этот экземпляр {@link Environment} к потоку,
     * в котором он создан {@link Thread}
     */
    public static Environment setForCurrentThread(@NotNull Environment environment) {
        Preconditions.notNull(environment, "Environment instance must not be null");
        INSTANCES.set(environment);
        return environment;
    }

    public static void finalizeForCurrentThread() {
        getForCurrentThread()
                .shutdown()
                .removeForCurrentThread();
    }

    /**
     * Отвязывает этот экземпляр {@link Environment} от потока,
     * в котором он создан {@link Thread}
     */
    public Environment removeForCurrentThread() {
        INSTANCES.remove();
        return this;
    }

    // After all actions

    public static void addAfterAllHook(@NotNull Runnable afterAllHook) {
        afterAllHooks.add(afterAllHook);
    }

    public static void executeAfterAllHooks() {
        afterAllHooks.forEach(Runnable::run);
    }

    // Check and Initialization

//    /**
//     * TODO: На этот метод нужны тесты
//     * Этот метод выполняет проверки валидности конфигурации для инициализации {@link Environment}
//     */
//    protected void checkEnvironmentConfiguration(@NotNull EnvironmentConfiguration environmentConfiguration) {
//        Preconditions.notNull(environmentConfiguration, "environmentConfiguration must not be null");
//        // TODO: Добавить необходимые проверки валидности
//    }

    /**
     * Этот метод отвечает за инициализацию всех провайдеров, описанных в конфигурации
     * @param environmentConfiguration конфигурация
     */
    protected void initEnvironment(@NotNull EnvironmentConfiguration environmentConfiguration,
                                   @Nullable String testName) {
        Preconditions.notNull(environmentConfiguration, "environmentConfiguration must not be null");
        EnvironmentLogger environmentLogger = EnvironmentLogger.of(environmentConfiguration, testName);
        environmentLogger.start();

        environmentConfiguration.getServiceConfigurations()
                .stream()
                .sorted(Comparator.comparingInt(ConfiguredServiceHolder::getOrder))
                .forEachOrdered(serviceHolder -> {
                    long startTime = System.nanoTime();
                    if (serviceHolder.isEnabled()) {
                        if (serviceHolder.isConfigured()) {
                            initService(serviceHolder.getServiceClass(),
                                    serviceHolder.getServiceImplementation(),
                                    serviceHolder.getServiceConfiguration());
                        } else {
                            initService(serviceHolder.getServiceClass(),
                                    serviceHolder.getServiceImplementation());
                        }
                    }
                    environmentLogger.addServiceRecord(serviceHolder, startTime, System.nanoTime());
                });

        environmentLogger.finish();
        this.environmentAttachment = TextAttachmentEntry.of(ENVIRONMENT_ATTACHMENT_NAME, environmentLogger.toString());
        environmentConfiguration.getEnvironmentAttachmentProcessor()
                .process(this.environmentAttachment);
    }

    protected void initEnvironment(@NotNull EnvironmentConfiguration environmentConfiguration) {
        initEnvironment(environmentConfiguration, null);
    }

    protected void initService(@NotNull Class<? extends Service> serviceClass,
                               @NotNull Class<? extends Service> serviceImplementation) {
        Preconditions.notNull(serviceClass, "serviceClass must not be null");
        Preconditions.notNull(serviceImplementation, "serviceImplementation must not be null");
        Service serviceInstance = newInstance(serviceImplementation);
        serviceInstance.init(this);
        register(serviceClass, serviceInstance);
    }

    protected void initService(@NotNull Class<? extends Service> serviceClass,
                               @NotNull Class<? extends Service> serviceImplementation,
                               @NotNull ServiceConfiguration serviceConfiguration) {
        Preconditions.notNull(serviceClass, "serviceClass must not be null");
        Preconditions.notNull(serviceImplementation, "serviceImplementation must not be null");
        Preconditions.notNull(serviceConfiguration, "serviceConfiguration must not be null");
        try {
            Service serviceInstance = newInstance(serviceImplementation);
            serviceInstance.init(this, serviceConfiguration);
            register(serviceClass, serviceInstance);
        } catch (Exception ex) {
           throw EnvironmentNotInitialized.exception(ENVIRONMENT_SERVICE_INITIALIZING_FAILED.getMessage(serviceImplementation.getCanonicalName()), ex);
        }
    }

}
