package io.perfeccionista.framework.exceptions.mapper;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class AppiumExceptionMapper implements ExceptionMapper {

    @Override
    public <T> ExceptionMapperResult<T> map(Supplier<T> supplier, String... exceptionMessageOptionalArgs) {
        // TODO: Implement
        return ExceptionMapperResult.empty();
    }

    @Override
    public ExceptionMapperResult<Void> map(Runnable runnable, String... exceptionMessageOptionalArgs) {
        // TODO: Implement
        return ExceptionMapperResult.empty();
    }

    @Override
    public PerfeccionistaRuntimeException createByName(@NotNull String name, @NotNull String message) {
        return null;
    }

}
