package io.perfeccionista.framework.exceptions.mapper;

import java.util.function.Supplier;

public interface ExceptionMapper {

    <T> T execute(Supplier<T> supplier);

    void execute(Runnable runnable);

}
