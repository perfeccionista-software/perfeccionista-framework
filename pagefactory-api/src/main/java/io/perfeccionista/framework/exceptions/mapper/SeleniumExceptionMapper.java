package io.perfeccionista.framework.exceptions.mapper;

import java.util.function.Supplier;

public class SeleniumExceptionMapper implements ExceptionMapper {

    @Override
    public <T> T execute(Supplier<T> supplier) {
        return null;
    }

    @Override
    public void execute(Runnable runnable) {

    }

}
