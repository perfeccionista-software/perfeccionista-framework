package io.perfeccionista.framework.invocation;

import io.perfeccionista.framework.exceptions.attachments.processor.AttachmentProcessor;
import io.perfeccionista.framework.exceptions.attachments.processor.DefaultAttachmentProcessor;
import io.perfeccionista.framework.invocation.runner.MultipleAttemptInvocationRunner;
import io.perfeccionista.framework.invocation.runner.DefaultInvocationInfoNameFormatter;
import io.perfeccionista.framework.invocation.runner.DefaultInvocationInfoStatisticsFormatter;
import io.perfeccionista.framework.invocation.runner.EmptyInvocationRunner;
import io.perfeccionista.framework.invocation.runner.InvocationInfoNameFormatter;
import io.perfeccionista.framework.invocation.runner.InvocationInfoStatisticsFormatter;
import io.perfeccionista.framework.invocation.runner.InvocationRunner;
import io.perfeccionista.framework.invocation.runner.SingleAttemptInvocationRunner;
import io.perfeccionista.framework.invocation.wrapper.MultipleAttemptInvocationWrapper;
import io.perfeccionista.framework.invocation.wrapper.SingleAttemptInvocationWrapper;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Set;

// TODO: Добавить возможность переопределять значения из проперти или переменных окружения
public class DefaultInvocationServiceConfiguration implements InvocationServiceConfiguration {
    private static final InvocationInfoNameFormatter DEFAULT_NAME_FORMATTER = new DefaultInvocationInfoNameFormatter(new HashMap<>());
    private static final InvocationInfoStatisticsFormatter DEFAULT_STATISTICS_FORMATTER = new DefaultInvocationInfoStatisticsFormatter();

    @Override
    public @NotNull Class<? extends InvocationRunner> getInvocationRunnerImplementation(@NotNull Class<?> invocationWrapper) {
        if (SingleAttemptInvocationWrapper.class.equals(invocationWrapper)) {
            return SingleAttemptInvocationRunner.class;
        }
        if (MultipleAttemptInvocationWrapper.class.equals(invocationWrapper)) {
            return MultipleAttemptInvocationRunner.class;
        }
        return EmptyInvocationRunner.class;
    }

    @Override
    public @NotNull InvocationInfoNameFormatter getInvocationInfoNameFormatter() {
        return DEFAULT_NAME_FORMATTER;
    }

    @Override
    public @NotNull InvocationInfoStatisticsFormatter getInvocationInfoStatisticsFormatter() {
        return DEFAULT_STATISTICS_FORMATTER;
    }

    @Override
    public @NotNull Set<AttachmentProcessor> getAttachmentProcessors() {
        return Set.of(new DefaultAttachmentProcessor());
    }
}
