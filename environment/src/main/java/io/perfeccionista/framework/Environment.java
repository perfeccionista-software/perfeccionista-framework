package io.perfeccionista.framework;

import io.perfeccionista.framework.exceptions.EnvironmentNotInitialized;
import io.perfeccionista.framework.exceptions.ServiceNotFound;
import io.perfeccionista.framework.utils.ReflectionUtils.Order;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.junit.platform.commons.util.Preconditions;
import io.perfeccionista.framework.exceptions.RegisterDuplicate;
import io.perfeccionista.framework.service.Service;
import io.perfeccionista.framework.service.ServiceConfiguration;
import io.perfeccionista.framework.service.UseService;
import io.perfeccionista.framework.utils.ReflectionUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.ENVIRONMENT_NOT_INITIALIZED;
import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.SERVICE_NOT_FOUND;
import static java.lang.Math.max;
import static java.lang.String.format;
import static org.junit.platform.commons.util.AnnotationUtils.findRepeatableAnnotations;
import static org.junit.platform.commons.util.ReflectionUtils.newInstance;
import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.CHECK_ARGUMENT_MUST_NOT_BE_NULL;
import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.SERVICE_REGISTER_BY_CLASS_DUPLICATE;
import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.SERVICE_REGISTER_CLASS_CAST;

/**
 * Экземпляры {@link Environment} инкапсулируют в себе среду выполнения
 * теста. Среда выполнения теста содержит тестовый класс, который запускается
 * в текущем потоке а так же набор {@link Service}.
 * {@link Service} регистрируются внутри {@link Environment} по классу.
 * {@link Environment} может содержать только один экземпляр {@link Service},
 * зарегистрированный по конкретному классу.
 *
 * Аннотация {@link UseEnvironmentConfiguration} указывает конфигурацию,
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

    protected final Class<?> testClass;
    protected final EnvironmentConfiguration configuration;
    protected final Map<Class<? extends Service>, Service> services = new HashMap<>();

    /**
     * Экземпляр {@link Environment} создается во время инициализации
     * Наследники этого класса должны иметь такой же конструктор
     * {@link io.perfeccionista.framework.extension.PerfeccionistaExtension}.
     */
    public Environment(@Nullable Class<?> testClass, @NotNull EnvironmentConfiguration environmentConfiguration) {
        this.testClass = testClass;
        this.configuration = environmentConfiguration;
        logger.debug(() -> "Environment configuration check");
        checkEnvironmentConfiguration(environmentConfiguration);
        logger.debug(() -> "Environment configuration initialization");
        initEnvironment(environmentConfiguration);
    }

    /**
     * Экземпляр {@link Environment} создается во время инициализации
     * Наследники этого класса должны иметь такой же конструктор
     * {@link io.perfeccionista.framework.extension.PerfeccionistaExtension}.
     */
    public Environment(@NotNull EnvironmentConfiguration environmentConfiguration) {
        this(null, environmentConfiguration);
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
     * @return тестовый класс в котором создан экземпляр {@link Environment}
     */
    public @Nullable Optional<Class<?>> getTestClass() {
        return Optional.ofNullable(testClass);
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
        Preconditions.notNull(environmentConfiguration.getEnvironmentClass(),
                CHECK_ARGUMENT_MUST_NOT_BE_NULL.getMessage("Environment class"));
        Preconditions.notNull(environmentConfiguration.getInvocationRunnerConfiguration(),
                CHECK_ARGUMENT_MUST_NOT_BE_NULL.getMessage("ActionRunnerConfiguration"));
        Preconditions.notNull(environmentConfiguration.getRepeatPolicy(),
                CHECK_ARGUMENT_MUST_NOT_BE_NULL.getMessage("RepeatPolicy"));
        Preconditions.notNull(environmentConfiguration.getTimeouts(),
                CHECK_ARGUMENT_MUST_NOT_BE_NULL.getMessage("Timeouts"));
    }

    /**
     * Этот метод отвечает за инициализацию всех провайдеров, описанных в конфигурации
     * @param environmentConfiguration конфигурация
     */
    protected void initEnvironment(@NotNull EnvironmentConfiguration environmentConfiguration) {
        EnvironmentLogger environmentLogger = EnvironmentLogger.of(environmentConfiguration);
        Map<Class<? extends Service>, UseService> servicesMap = new HashMap<>();

        ReflectionUtils.getInheritedClasses(EnvironmentConfiguration.class, environmentConfiguration.getClass(), Order.ASC)
                .forEach(configurationClass -> findRepeatableAnnotations(configurationClass, UseService.class)
                        .forEach(annotation -> servicesMap.put(annotation.service(), annotation)));

        servicesMap.entrySet().stream()
                .sorted(Comparator.comparingInt(entry -> entry.getValue().order()))
                .forEachOrdered(entry -> {
                    UseService annotation = entry.getValue();
                    Class<? extends Service> serviceClass = annotation.service();
                    environmentLogger.addServiceRecord(annotation);
                    if (!annotation.disabled()) {
                        Class<? extends ServiceConfiguration> configurationClass = annotation.configuration();
                        if (configurationClass.equals(ServiceConfiguration.class)) {
                            initService(serviceClass);
                        } else {
                            ServiceConfiguration serviceConfiguration = newInstance(configurationClass);
                            initService(serviceClass, serviceConfiguration);
                        }
                    }
                });

        logger.info(environmentLogger::toString);
    }

    protected void initService(@NotNull Class<? extends Service> serviceClass) {
        Service serviceInstance = newInstance(serviceClass);
        serviceInstance.init(this, null);
        register(serviceClass, serviceInstance);
    }

    protected void initService(@NotNull Class<? extends Service> serviceClass,
                               @NotNull ServiceConfiguration serviceConfiguration) {
        Optional<Class<? extends Service>> customImplementation = serviceConfiguration.getImplementation();
        // TODO: Проверить соответствие провайдера и конфигурации и привести их к <T>
        Class<? extends Service> serviceImplementation = customImplementation.orElse(serviceClass);
        Service serviceInstance = newInstance(serviceImplementation);
        serviceInstance.init(this, serviceConfiguration);
        register(serviceClass, serviceInstance);
    }

    /**
     * Класс который выводит в лог содержимое {@link Environment}
     * Он должен собирать внутри информацию обо всех сконфигурированных сервисах,
     * включая отключенные экземпляры.
     *
     *  Информация выводится в виде:
     *  -----------------------------------------------------------------------------------------------------------
     *  Environment configuration
     *  -----------------------------------------------------------------------------------------------------------
     *      Functionality                  Implementation class
     *  -----------------------------------------------------------------------------------------------------------
     *      Environment class              Environment.class
     *      Action runner configuration    MyActionRunnerConfiguration.class
     *      Timeouts                       MyTimeouts.class
     *      Repeat policy                  MyRepeatPolicy.class
     *  -----------------------------------------------------------------------------------------------------------
     *  Providers
     *  -----------------------------------------------------------------------------------------------------------
     *      Service class                  Provider configuration                             Enabled     Order
     *  -----------------------------------------------------------------------------------------------------------
     *      DataSourceInstanceProvider     MyDataSourceInstanceProviderConfiguration.class    disabled    0
     *  -----------------------------------------------------------------------------------------------------------
     *
     */
    public static class EnvironmentLogger {

        private final EnvironmentConfiguration environmentConfiguration;
        private final List<UseService> services = new ArrayList<>();

        private int column1 = 27;
        private int column2 = 0;
        private int column3 = 8;
        private int column4 = 5;

        private EnvironmentLogger(EnvironmentConfiguration environmentConfiguration) {
            this.environmentConfiguration = environmentConfiguration;
            column2 = getMaxLength(environmentConfiguration.getEnvironmentClass().getCanonicalName(), column2);
            column2 = getMaxLength(environmentConfiguration.getInvocationRunnerConfiguration().getClass().getCanonicalName(), column2);
            column2 = getMaxLength(environmentConfiguration.getRepeatPolicy().getClass().getCanonicalName(), column2);
            column2 = getMaxLength(environmentConfiguration.getTimeouts().getClass().getCanonicalName(), column2);
        }

        public static EnvironmentLogger of(@NotNull EnvironmentConfiguration environmentConfiguration) {
            return new EnvironmentLogger(environmentConfiguration);
        }

        public void addServiceRecord(@NotNull UseService annotation) {
            services.add(annotation);
            column1 = getMaxLength(annotation.service().getCanonicalName(), column1);
            column2 = getMaxLength(annotation.configuration().getCanonicalName(), column2);
        }

        /**
         * @return
         */
        @Override
        @SuppressWarnings("Duplicates")
        public String toString() {
            String tab = "    ";
            String splitter = makeSplitterForLength(4 + column1 + 4 + column2 + 4 + column3 + 4 + column4 + 4);
            StringBuilder sb = new StringBuilder();
            sb.append("\n");
            sb.append(splitter).append("\n");
            sb.append("Environment configuration").append("\n");
            sb.append(splitter).append("\n");
            sb.append(tab)
                    .append(formatToColumn(column1, "Functionality"))
                    .append(tab)
                    .append(formatToColumn(column2, "Implementation class")).append("\n");
            sb.append(splitter).append("\n");
            sb.append(tab)
                    .append(formatToColumn(column1, "Environment class"))
                    .append(tab)
                    .append(formatToColumn(column2, environmentConfiguration.getEnvironmentClass().getCanonicalName())).append("\n");
            sb.append(tab)
                    .append(formatToColumn(column1, "Invocation runner configuration"))
                    .append(tab)
                    .append(formatToColumn(column2, environmentConfiguration.getInvocationRunnerConfiguration().getClass().getCanonicalName())).append("\n");
            sb.append(tab)
                    .append(formatToColumn(column1, "Repeat policy"))
                    .append(tab)
                    .append(formatToColumn(column2, environmentConfiguration.getRepeatPolicy().getClass().getCanonicalName())).append("\n");
            sb.append(tab)
                    .append(formatToColumn(column1, "Timeouts"))
                    .append(tab)
                    .append(formatToColumn(column2, environmentConfiguration.getTimeouts().getClass().getCanonicalName())).append("\n");
            sb.append(splitter).append("\n");
            sb.append("Services").append("\n");
            sb.append(splitter).append("\n");
            sb.append(tab)
                    .append(formatToColumn(column1, "Service class"))
                    .append(tab)
                    .append(formatToColumn(column2, "Service configuration"))
                    .append(tab)
                    .append(formatToColumn(column3, "Enabled"))
                    .append(tab)
                    .append(formatToColumn(column4, "Order")).append("\n");
            sb.append(splitter).append("\n");
            services.forEach(annotation ->
                    sb.append(tab)
                            .append(formatToColumn(column1, annotation.service().getCanonicalName()))
                            .append(tab)
                            .append(formatToColumn(column2, annotation.configuration().getCanonicalName()))
                            .append(tab)
                            .append(formatToColumn(column3, annotation.disabled() ? "disabled" : "enabled"))
                            .append(tab)
                            .append(formatToColumn(column4, String.valueOf(annotation.order()))).append("\n"));
            sb.append(splitter).append("\n");
            return sb.toString();
        }

        private int getMaxLength(String string, int columnLength) {
            return max(string.length(), columnLength);
        }

        private String makeSplitterForLength(int length) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < length; i++) {
                sb.append("-");
            }
            return sb.toString();
        }

        private String formatToColumn(int columnLength, String columnText) {
            return format("%1$-" + columnLength + "s", columnText);
        }

    }

}
