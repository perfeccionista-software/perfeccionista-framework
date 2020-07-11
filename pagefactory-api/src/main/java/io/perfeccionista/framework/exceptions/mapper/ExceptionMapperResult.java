package io.perfeccionista.framework.exceptions.mapper;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class ExceptionMapperResult<T> {

    protected final PerfeccionistaException exception;
    protected final T result;

    private ExceptionMapperResult(PerfeccionistaException exception, T result) {
        this.exception = exception;
        this.result = result;
    }

    public static <T> ExceptionMapperResult<T> empty() {
        return new ExceptionMapperResult<>(null, null);
    }

    public static <T> ExceptionMapperResult<T> success(@NotNull T result) {
        return new ExceptionMapperResult<>(null, result);
    }

    public static <T> ExceptionMapperResult<T> failure(@NotNull PerfeccionistaException exception) {
        return new ExceptionMapperResult<>(exception, null);
    }

    public ExceptionMapperResult<T> ifException(Consumer<PerfeccionistaException> exceptionConsumer) {
        if (exception != null) {
            exceptionConsumer.accept(exception);
        }
        return this;
    }

    public T getResult() {
        return result;
    }

}
