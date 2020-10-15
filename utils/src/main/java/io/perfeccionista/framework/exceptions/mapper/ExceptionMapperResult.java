package io.perfeccionista.framework.exceptions.mapper;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class ExceptionMapperResult<T> {

    protected final PerfeccionistaRuntimeException exception;
    protected final T result;

    private ExceptionMapperResult(PerfeccionistaRuntimeException exception, T result) {
        this.exception = exception;
        this.result = result;
    }

    public static <T> ExceptionMapperResult<T> empty() {
        return new ExceptionMapperResult<>(null, null);
    }

    public static <T> ExceptionMapperResult<T> success(@NotNull T result) {
        return new ExceptionMapperResult<>(null, result);
    }

    public static <T> ExceptionMapperResult<T> failure(@NotNull PerfeccionistaRuntimeException exception) {
        return new ExceptionMapperResult<>(exception, null);
    }

    public ExceptionMapperResult<T> ifException(Consumer<PerfeccionistaRuntimeException> exceptionConsumer) {
        if (exception != null) {
            exceptionConsumer.accept(exception);
        }
        return this;
    }

    public T getResult() {
        return result;
    }

}
