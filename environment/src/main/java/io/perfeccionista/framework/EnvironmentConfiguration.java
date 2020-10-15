package io.perfeccionista.framework;

import org.jetbrains.annotations.NotNull;
import io.perfeccionista.framework.invocation.runner.InvocationRunner;
import io.perfeccionista.framework.invocation.runner.InvocationRunnerConfiguration;
import io.perfeccionista.framework.invocation.timeouts.Timeouts;
import io.perfeccionista.framework.repeater.RepeatPolicy;
import io.perfeccionista.framework.repeater.TestRepeatedOnCondition;

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
     * запуске всех тестов внутри аннотарованного тестового класса.
     * @see Environment
     */
    @NotNull
    default Class<? extends Environment> getEnvironmentClass() {
        return Environment.class;
    }

    /**
     * @return конфигурацию для используемых в проекте {@link InvocationRunner}.
     * Она НЕ должна быть null.
     * @see InvocationRunnerConfiguration
     * @see InvocationRunner
     */
    @NotNull InvocationRunnerConfiguration getInvocationRunnerConfiguration();

    /**
     * @return используемую политику перезапусков тестов,
     * которые используются в проекте по умолчанию. Она НЕ должна быть null.
     * @see RepeatPolicy
     * @see TestRepeatedOnCondition
     */
    @NotNull RepeatPolicy getRepeatPolicy();

    /**
     * @return конфигурацию используемых в проекте таймаутов. Она НЕ должна быть null.
     * @see Timeouts
     */
    @NotNull Timeouts getTimeouts();

}
