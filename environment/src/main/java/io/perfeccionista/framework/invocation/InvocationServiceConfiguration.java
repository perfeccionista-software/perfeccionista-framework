package io.perfeccionista.framework.invocation;

import io.perfeccionista.framework.invocation.runner.InvocationRunner;
import io.perfeccionista.framework.service.ServiceConfiguration;
import org.jetbrains.annotations.NotNull;

public interface InvocationServiceConfiguration extends ServiceConfiguration {

    @NotNull Class<? extends InvocationRunner> getInvocationRunnerImplementation(@NotNull Class<?> invocationWrapper);

}
