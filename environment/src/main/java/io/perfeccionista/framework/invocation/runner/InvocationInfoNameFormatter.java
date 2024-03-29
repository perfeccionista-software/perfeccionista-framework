package io.perfeccionista.framework.invocation.runner;

import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface InvocationInfoNameFormatter {

    String format(@NotNull String name, String... args);

}
