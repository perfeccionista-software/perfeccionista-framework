package io.perfeccionista.framework.fixture;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class FixtureTearDownResult<T> {

    private final T result;
    private final RuntimeException exception;
    private Consumer<T> resultProcessor = null;
    private Consumer<RuntimeException> exceptionProcessor = null;

    private FixtureTearDownResult(T result, RuntimeException exception) {
        this.result = result;
        this.exception = exception;
    }

    public static <T> FixtureTearDownResult<T> of(@NotNull Supplier<T> resultSupplier) {
        T result;
        try {
            result = resultSupplier.get();
            return FixtureTearDownResult.of(result);
        } catch (RuntimeException exception) {
            return FixtureTearDownResult.exception(exception);
        }
    }

    public static <T> FixtureTearDownResult<T> of(@Nullable T result) {
        return new FixtureTearDownResult<>(result, null);
    }

    public static <T> FixtureTearDownResult<T> of(@Nullable T result, @NotNull RuntimeException exception) {
        return new FixtureTearDownResult<>(result, exception);
    }

    public static <T> FixtureTearDownResult<T> exception(@NotNull RuntimeException exception) {
        return new FixtureTearDownResult<>(null, exception);
    }

    public static <T> FixtureTearDownResult<T> empty() {
        return new FixtureTearDownResult<>(null, null);
    }

    public Optional<T> getResult() {
        return Optional.ofNullable(result);
    }

    public Optional<RuntimeException> getException() {
        return Optional.ofNullable(exception);
    }

    public FixtureTearDownResult<T> setResultProcessor(@NotNull Consumer<T> resultProcessor) {
        this.resultProcessor = resultProcessor;
        return this;
    }

    public FixtureTearDownResult<T> setExceptionProcessor(@NotNull Consumer<RuntimeException> exceptionProcessor) {
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
