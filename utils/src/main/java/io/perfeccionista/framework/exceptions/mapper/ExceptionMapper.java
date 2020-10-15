package io.perfeccionista.framework.exceptions.mapper;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public interface ExceptionMapper {

    @NotNull <T> ExceptionMapperResult<T> map(@NotNull Supplier<T> supplier, String... exceptionMessageOptionalArgs);

    @NotNull ExceptionMapperResult<Void> map(@NotNull Runnable runnable, String... exceptionMessageOptionalArgs);

    @NotNull PerfeccionistaRuntimeException createByName(@NotNull String name, @NotNull String message);

}
