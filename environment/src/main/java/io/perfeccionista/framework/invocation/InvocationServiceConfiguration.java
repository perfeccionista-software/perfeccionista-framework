package io.perfeccionista.framework.invocation;

import io.perfeccionista.framework.exceptions.attachments.processor.AttachmentProcessor;
import io.perfeccionista.framework.invocation.runner.InvocationInfoNameFormatter;
import io.perfeccionista.framework.invocation.runner.InvocationInfoStatisticsFormatter;
import io.perfeccionista.framework.invocation.runner.InvocationRunner;
import io.perfeccionista.framework.service.ServiceConfiguration;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public interface InvocationServiceConfiguration extends ServiceConfiguration {

    @NotNull Class<? extends InvocationRunner> getInvocationRunnerImplementation(@NotNull Class<?> invocationWrapper);

    @NotNull InvocationInfoNameFormatter getInvocationInfoNameFormatter();

    @NotNull InvocationInfoStatisticsFormatter getInvocationInfoStatisticsFormatter();

    @NotNull Set<AttachmentProcessor> getAttachmentProcessors();

}
