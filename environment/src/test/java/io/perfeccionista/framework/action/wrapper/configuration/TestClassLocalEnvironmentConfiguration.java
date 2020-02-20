package io.perfeccionista.framework.action.wrapper.configuration;

import org.jetbrains.annotations.NotNull;
import io.perfeccionista.framework.EnvironmentConfiguration;
import io.perfeccionista.framework.action.runner.ActionRunner;
import io.perfeccionista.framework.action.runner.ActionRunnerConfiguration;
import io.perfeccionista.framework.action.runner.CheckActionRunner;
import io.perfeccionista.framework.action.runner.EmptyActionRunner;
import io.perfeccionista.framework.action.runner.LogicActionRunner;
import io.perfeccionista.framework.action.timeouts.DefaultTimeouts;
import io.perfeccionista.framework.action.timeouts.Timeouts;
import io.perfeccionista.framework.action.wrappers.CheckActionWrapper;
import io.perfeccionista.framework.action.wrappers.LogicActionWrapper;
import io.perfeccionista.framework.repeater.RepeatPolicy;

import static org.mockito.Mockito.mock;

public class TestClassLocalEnvironmentConfiguration implements EnvironmentConfiguration {

    private final ActionRunnerConfiguration actionRunnerConfiguration;
    private final RepeatPolicy repeatPolicy;
    private final Timeouts timeouts;

    public TestClassLocalEnvironmentConfiguration() {
        this.actionRunnerConfiguration = new TestActionRunnerConfiguration();
        this.repeatPolicy = mock(RepeatPolicy.class);
        this.timeouts = new DefaultTimeouts();
    }

    @Override
    public @NotNull ActionRunnerConfiguration getActionRunnerConfiguration() {
        return actionRunnerConfiguration;
    }

    @Override
    public @NotNull RepeatPolicy getRepeatPolicy() {
        return repeatPolicy;
    }

    @Override
    public @NotNull Timeouts getTimeouts() {
        return timeouts;
    }

    private static class TestActionRunnerConfiguration implements ActionRunnerConfiguration {

        @Override
        public @NotNull Class<? extends ActionRunner> getActionRunnerImplementation(@NotNull Class<?> actionWrapper) {
            if (LogicActionWrapper.class.equals(actionWrapper)) {
                return LogicActionRunner.class;
            } else if (CheckActionWrapper.class.equals(actionWrapper)) {
                return CheckActionRunner.class;
            }
            return EmptyActionRunner.class;
        }

    }

}
