package io.perfeccionista.framework.exceptions.mapper;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class AppiumExceptionMapper implements ExceptionMapper {

    @Override
    public <T> T map(Supplier<T> supplier) {
        return null;
    }

    @Override
    public void map(Runnable runnable) {

    }

    @Override
    public PerfeccionistaException createByName(@NotNull String name, @NotNull String message) {
        return null;
    }

}
