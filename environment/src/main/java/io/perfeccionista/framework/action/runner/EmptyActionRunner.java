package io.perfeccionista.framework.action.runner;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import io.perfeccionista.framework.Environment;

import java.time.Duration;
import java.util.function.Supplier;

public class EmptyActionRunner implements ActionRunner {

    @Override
    public <T> T run(@NotNull Environment environment, @Nullable String name, @NotNull Supplier<T> supplier, @Nullable Duration timeout) {
        return supplier.get();
    }

}
