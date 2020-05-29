package io.perfeccionista.framework.exceptions.mapper;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public interface ExceptionMapper {

    <T> T map(Supplier<T> supplier);

    void map(Runnable runnable);

    PerfeccionistaException createByName(@NotNull String name, @NotNull String message);

}
