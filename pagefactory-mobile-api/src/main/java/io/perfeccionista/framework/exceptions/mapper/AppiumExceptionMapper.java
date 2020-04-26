package io.perfeccionista.framework.exceptions.mapper;

import java.util.function.Supplier;

public class AppiumExceptionMapper implements ExceptionMapper {

    @Override
    public <T> T map(Supplier<T> supplier) {
        return null;
    }

    @Override
    public void map(Runnable runnable) {

    }

}
