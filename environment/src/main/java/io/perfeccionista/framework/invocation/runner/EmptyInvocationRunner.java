package io.perfeccionista.framework.invocation.runner;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import io.perfeccionista.framework.Environment;

import java.time.Duration;
import java.util.function.Supplier;

public class EmptyInvocationRunner implements InvocationRunner {

    @Override
    public <T> T run(@NotNull Environment environment, @Nullable InvocationInfo invocation, @NotNull Supplier<T> supplier, @NotNull Duration timeout) {
        return supplier.get();
    }

}
