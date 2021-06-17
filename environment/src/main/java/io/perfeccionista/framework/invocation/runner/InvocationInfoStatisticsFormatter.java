package io.perfeccionista.framework.invocation.runner;

import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface InvocationInfoStatisticsFormatter {

    String format(@NotNull InvocationInfo invocationInfo);

}
