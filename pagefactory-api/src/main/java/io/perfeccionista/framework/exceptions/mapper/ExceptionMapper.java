package io.perfeccionista.framework.exceptions.mapper;

import java.util.function.Supplier;

public interface ExceptionMapper {

    <T> T map(Supplier<T> supplier);

    void map(Runnable runnable);

}
