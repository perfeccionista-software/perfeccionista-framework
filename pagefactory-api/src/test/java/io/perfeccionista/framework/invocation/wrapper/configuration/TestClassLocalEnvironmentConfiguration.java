package io.perfeccionista.framework.invocation.wrapper.configuration;

import org.jetbrains.annotations.NotNull;
import io.perfeccionista.framework.EnvironmentConfiguration;
import io.perfeccionista.framework.invocation.runner.InvocationRunner;
import io.perfeccionista.framework.invocation.runner.InvocationRunnerConfiguration;
import io.perfeccionista.framework.invocation.runner.CheckInvocationRunner;
import io.perfeccionista.framework.invocation.runner.EmptyInvocationRunner;
import io.perfeccionista.framework.invocation.runner.LogicInvocationRunner;
import io.perfeccionista.framework.invocation.timeouts.DefaultTimeouts;
import io.perfeccionista.framework.invocation.timeouts.Timeouts;
import io.perfeccionista.framework.invocation.wrappers.CheckActionWrapper;
import io.perfeccionista.framework.invocation.wrappers.LogicActionWrapper;
import io.perfeccionista.framework.repeater.RepeatPolicy;

import static org.mockito.Mockito.mock;

public class TestClassLocalEnvironmentConfiguration implements EnvironmentConfiguration {

    private final InvocationRunnerConfiguration actionRunnerConfiguration;
    private final RepeatPolicy repeatPolicy;
    private final Timeouts timeouts;

    public TestClassLocalEnvironmentConfiguration() {
        this.actionRunnerConfiguration = new TestInvocationRunnerConfiguration();
        this.repeatPolicy = mock(RepeatPolicy.class);
        this.timeouts = new DefaultTimeouts();
    }

    @Override
    public @NotNull InvocationRunnerConfiguration getActionRunnerConfiguration() {
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

    private static class TestInvocationRunnerConfiguration implements InvocationRunnerConfiguration {

        @Override
        public @NotNull Class<? extends InvocationRunner> getActionRunnerImplementation(@NotNull Class<?> actionWrapper) {
            if (LogicActionWrapper.class.equals(actionWrapper)) {
                return LogicInvocationRunner.class;
            } else if (CheckActionWrapper.class.equals(actionWrapper)) {
                return CheckInvocationRunner.class;
            }
            return EmptyInvocationRunner.class;
        }

    }

}
