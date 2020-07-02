package io.perfeccionista.framework.invocation.runner;

import io.perfeccionista.framework.invocation.wrappers.CheckActionWrapper;
import io.perfeccionista.framework.invocation.wrappers.LogicActionWrapper;
import org.jetbrains.annotations.NotNull;

public class SeleniumInvocationRunnerConfiguration implements InvocationRunnerConfiguration {

    @Override
    public @NotNull Class<? extends InvocationRunner> getActionRunnerImplementation(@NotNull Class<?> actionWrapper) {
        if (CheckActionWrapper.class.equals(actionWrapper)) {
            return SeleniumCheckInvocationRunner.class;
        }
        if (LogicActionWrapper.class.equals(actionWrapper)) {
            return SeleniumLogicInvocationRunner.class;
        }
        return EmptyInvocationRunner.class;
    }

}
