package io.perfeccionista.framework.invocation.runner;

import io.perfeccionista.framework.invocation.wrappers.CheckInvocationWrapper;
import io.perfeccionista.framework.invocation.wrappers.LogicInvocationWrapper;
import org.jetbrains.annotations.NotNull;

public class DefaultInvocationRunnerConfiguration implements InvocationRunnerConfiguration {

    @Override
    public @NotNull Class<? extends InvocationRunner> getInvocationRunnerImplementation(@NotNull Class<?> invocationWrapper) {
        if (CheckInvocationWrapper.class.equals(invocationWrapper)) {
            return CheckInvocationRunner.class;
        }
        if (LogicInvocationWrapper.class.equals(invocationWrapper)) {
            return LogicInvocationRunner.class;
        }
        return EmptyInvocationRunner.class;
    }

}
