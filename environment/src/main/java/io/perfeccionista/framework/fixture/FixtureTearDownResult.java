package io.perfeccionista.framework.fixture;

import io.perfeccionista.framework.preconditions.Preconditions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class FixtureTearDownResult<T> {

    private final T result;
    private final RuntimeException exception;
    private BiConsumer<T, RuntimeException> resultProcessor = (result, runtimeException) -> {
        if (Objects.nonNull(runtimeException)) {
            throw runtimeException;
        }
    };

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

    public FixtureTearDownResult<T> setResultProcessor(@NotNull BiConsumer<T, RuntimeException> resultProcessor) {
        Preconditions.notNull(resultProcessor, "Exception processor must not be null");
        this.resultProcessor = resultProcessor;
        return this;
    }

    public void process() {
        resultProcessor.accept(result, exception);
    }

}
