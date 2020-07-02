package io.perfeccionista.framework.invocation.runner;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import io.perfeccionista.framework.Environment;

import java.time.Duration;
import java.util.function.Supplier;

public class EmptyInvocationRunner implements InvocationRunner {

    @Override
    public <T> T run(@NotNull Environment environment, @Nullable InvocationName name, @NotNull Supplier<T> supplier, @Nullable Duration timeout) {
        return supplier.get();
    }

}
