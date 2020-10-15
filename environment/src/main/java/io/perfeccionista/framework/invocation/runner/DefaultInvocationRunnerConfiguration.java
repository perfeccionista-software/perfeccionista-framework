package io.perfeccionista.framework.invocation.runner;

import org.jetbrains.annotations.NotNull;

public class DefaultInvocationRunnerConfiguration implements InvocationRunnerConfiguration {

    @Override
    public @NotNull Class<? extends InvocationRunner> getInvocationRunnerImplementation(@NotNull Class<?> invocationWrapper) {
        return EmptyInvocationRunner.class;
    }

}
