package io.perfeccionista.framework;

import io.perfeccionista.framework.logging.Logger;
import io.perfeccionista.framework.service.ConfiguredServiceHolder;
import io.perfeccionista.framework.service.Service;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.Set;

/**
 * Базовый интерфейс для конфигурирования {@link Environment}.
 * Реализация этого интерфейса в общем случае выглядит следующим образом:
 * <pre>
 * {@code
 * @UseService(value = MyService.class, configuration = MyServiceConfiguration.class)
 * @UseService(value = AnotherService.class, configuration = AnotherServiceConfiguration.class)
 * class MyEnvironmentConfiguration extends EnvironmentConfiguration {
 *
 *    private final ActionRunnerConfiguration actionRunnerConfiguration;
 *    private final RepeatPolicy repeatPolicy;
 *    private final Timeouts timeouts;
 *
 *    public TestClassLocalEnvironmentConfiguration() {
 *        this.actionRunnerConfiguration = new TestActionRunnerConfiguration();
 *        this.repeatPolicy = new DefaultRepeatPolicy();
 *        this.timeouts = new DefaultTimeouts();
 *    }
 *
 *    @Override
 *    public Class<? extends Environment> getEnvironmentClass() {
 *        return MyEnvironment.class;
 *    }
 *
 *    @Override
 *    public @NotNull ActionRunnerConfiguration getActionRunnerConfiguration() {
 *        return actionRunnerConfiguration;
 *    }
 *
 *    @Override
 *    public @NotNull RepeatPolicy getRepeatPolicy() {
 *        return repeatPolicy;
 *    }
 *
 *    @Override
 *    public @NotNull Timeouts getTimeouts() {
 *        return timeouts;
 *    }
 *
 * }
 * </pre>
 *
 * Над классом конфигурации находятся аннотации для конфигурации
 * {@link io.perfeccionista.framework.service.Service}, которые
 * должны быть инициализированы в этом экземпляре.
 * Если класс конфигурации расширяет другой класс, то настройки провайдеров из
 * класса-предка также учитываются в конфигурировании {@link Environment}
 * При необходимости можно переопределить настройки используемого
 * {@link io.perfeccionista.framework.service.Service} или
 * выключить их через параметры соответствующих аннотаций
 *
 * @see {@link Environment}
 */
public interface EnvironmentConfiguration {

    /**
     * @return реализацию {@link Environment}, которая будет использована при
     * запуске всех тестов внутри аннотированного тестового класса.
     * @see Environment
     */
    default @NotNull Class<? extends Environment> getEnvironmentClass() {
        return Environment.class;
    }

    /**
     * Набор сервисов и конфигураций для замены дефолных сервисов
     * @return
     */
    @NotNull Map<Class<? extends Service>, ConfiguredServiceHolder> getServices();

    /**
     * @return набор переменных, использующихся в тестах.
     */
    @NotNull Set<String> getScanPackages();

    /**
     * @return Возвращает кастомный логгер, который будет использоваться в тесте.
     * Если не задан, то используется логгер по умолчанию.
     */
    default Optional<Class<? extends Logger>> getLoggerClass() {
        return Optional.empty();
    }

}
