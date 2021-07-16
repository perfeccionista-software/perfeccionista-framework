package io.perfeccionista.framework.invocation;

import io.perfeccionista.framework.invocation.runner.CheckInvocationRunner;
import io.perfeccionista.framework.invocation.runner.DefaultInvocationInfoNameFormatter;
import io.perfeccionista.framework.invocation.runner.DefaultInvocationInfoStatisticsFormatter;
import io.perfeccionista.framework.invocation.runner.EmptyInvocationRunner;
import io.perfeccionista.framework.invocation.runner.InvocationInfoNameFormatter;
import io.perfeccionista.framework.invocation.runner.InvocationInfoStatisticsFormatter;
import io.perfeccionista.framework.invocation.runner.InvocationRunner;
import io.perfeccionista.framework.invocation.runner.LogicInvocationRunner;
import io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper;
import io.perfeccionista.framework.invocation.wrapper.LogicInvocationWrapper;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

// TODO: Добавить возможность переопределять значения из проперти или переменных окружения
public class DefaultInvocationServiceConfiguration implements InvocationServiceConfiguration {
    private static final InvocationInfoNameFormatter DEFAULT_NAME_FORMATTER = new DefaultInvocationInfoNameFormatter(Map.of());
    private static final InvocationInfoStatisticsFormatter DEFAULT_STATISTICS_FORMATTER = new DefaultInvocationInfoStatisticsFormatter();

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

    @Override
    public @NotNull InvocationInfoNameFormatter getInvocationInfoNameFormatter() {
        return DEFAULT_NAME_FORMATTER;
    }

    @Override
    public @NotNull InvocationInfoStatisticsFormatter getInvocationInfoStatisticsFormatter() {
        return DEFAULT_STATISTICS_FORMATTER;
    }

}
