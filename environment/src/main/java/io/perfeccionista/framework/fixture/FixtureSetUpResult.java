package io.perfeccionista.framework.fixture;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.function.Consumer;

public class FixtureSetUpResult<T> {

    private final T result;
    private final Exception exception;
    private Consumer<Exception> exceptionProcessor = null;

    private FixtureSetUpResult(T result, Exception exception) {
        this.result = result;
        this.exception = exception;
    }

    public static <T> FixtureSetUpResult<T> of(@Nullable T result, @Nullable Exception exception) {
        return new FixtureSetUpResult<>(result, exception);
    }

    public Optional<T> getResult() {
        return Optional.ofNullable(result);
    }

    public Optional<Exception> getException() {
        return Optional.ofNullable(exception);
    }

    public FixtureSetUpResult<T> setExceptionProcessor(@NotNull Consumer<Exception> exceptionProcessor) {
        this.exceptionProcessor = exceptionProcessor;
        return this;
    }

    public FixtureSetUpResult<T> process() {
        if (null != exception && null != exceptionProcessor) {
            exceptionProcessor.accept(exception);
        }
        return this;
    }

}
