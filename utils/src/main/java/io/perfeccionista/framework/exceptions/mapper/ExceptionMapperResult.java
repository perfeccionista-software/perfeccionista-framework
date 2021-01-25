package io.perfeccionista.framework.exceptions.mapper;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.function.Consumer;

public class ExceptionMapperResult<T> {

    protected PerfeccionistaRuntimeException exception;
    protected T result;

    private ExceptionMapperResult(PerfeccionistaRuntimeException exception, T result) {
        this.exception = exception;
        this.result = result;
    }

    public static <T> ExceptionMapperResult<T> empty() {
        return new ExceptionMapperResult<>(null, null);
    }

    public static <T> ExceptionMapperResult<T> success(@Nullable T result) {
        return new ExceptionMapperResult<>(null, result);
    }

    public static <T> ExceptionMapperResult<T> failure(@NotNull PerfeccionistaRuntimeException exception) {
        return new ExceptionMapperResult<>(exception, null);
    }

    public boolean isSuccess() {
        return Objects.isNull(exception);
    }

    public boolean isException() {
        return Objects.nonNull(exception);
    }

    public ExceptionMapperResult<T> ifException(Consumer<PerfeccionistaRuntimeException> exceptionConsumer) {
        if (exception != null) {
            exceptionConsumer.accept(exception);
        }
        return this;
    }

    public ExceptionMapperResult<T> setResultIfException(T alternativeResult) {
        if (exception != null) {
            result = alternativeResult;
        }
        return this;
    }

    public @Nullable T getResult() {
        return result;
    }

    public @Nullable PerfeccionistaRuntimeException getException() {
        return exception;
    }

}
