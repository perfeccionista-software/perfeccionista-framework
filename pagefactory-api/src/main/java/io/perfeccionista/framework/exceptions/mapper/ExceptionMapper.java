package io.perfeccionista.framework.exceptions.mapper;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public interface ExceptionMapper {

    <T> ExceptionMapperResult<T> map(Supplier<T> supplier, String... exceptionMessageOptionalArgs);

    ExceptionMapperResult<Void> map(Runnable runnable, String... exceptionMessageOptionalArgs);

    PerfeccionistaException createByName(@NotNull String name, @NotNull String message);

}
