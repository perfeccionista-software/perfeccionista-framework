package io.perfeccionista.framework.fixture;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.function.Consumer;

public class FixtureTearDownResult<T> {

    private final T result;
    private final Exception exception;
    private Consumer<T> resultProcessor = null;
    private Consumer<Exception> exceptionProcessor = null;

    private FixtureTearDownResult(T result, Exception exception) {
        this.result = result;
        this.exception = exception;
    }

    public static <T> FixtureTearDownResult<T> of(@Nullable T result) {
        return new FixtureTearDownResult<>(result, null);
    }

    public static <T> FixtureTearDownResult<T> of(@Nullable T result, @NotNull Exception exception) {
        return new FixtureTearDownResult<>(result, exception);
    }

    public static <T> FixtureTearDownResult<T> exception(@NotNull Exception exception) {
        return new FixtureTearDownResult<>(null, exception);
    }

    public Optional<T> getResult() {
        return Optional.ofNullable(result);
    }

    public Optional<Exception> getException() {
        return Optional.ofNullable(exception);
    }

    public FixtureTearDownResult<T> setResultProcessor(@NotNull Consumer<T> resultProcessor) {
        this.resultProcessor = resultProcessor;
        return this;
    }

    public FixtureTearDownResult<T> setExceptionProcessor(@NotNull Consumer<Exception> exceptionProcessor) {
        this.exceptionProcessor = exceptionProcessor;
        return this;
    }

    public void process() {
        if (null != result && null != resultProcessor) {
            resultProcessor.accept(result);
        }
        if (null != exception && null != exceptionProcessor) {
            exceptionProcessor.accept(exception);
        }
    }

}
