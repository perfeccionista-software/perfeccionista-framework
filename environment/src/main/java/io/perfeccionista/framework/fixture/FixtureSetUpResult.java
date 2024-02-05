package io.perfeccionista.framework.fixture;

import io.perfeccionista.framework.exceptions.RequiredFixtureResultIsNull;
import io.perfeccionista.framework.preconditions.Preconditions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.REQUIRED_FIXTURE_RESULT_IS_NULL;

public class FixtureSetUpResult<T> {

    private final T result;
    private final RuntimeException exception;
    private Consumer<RuntimeException> exceptionProcessor = (runtimeException) -> {
        throw runtimeException;
    };

    private FixtureSetUpResult(T result, RuntimeException exception) {
        this.result = result;
        this.exception = exception;
    }

    public static <T> FixtureSetUpResult<T> of(@NotNull Supplier<T> resultSupplier) {
        T result;
        try {
            result = resultSupplier.get();
            return new FixtureSetUpResult<>(result, null);
        } catch (RuntimeException exception) {
            return new FixtureSetUpResult<>(null, exception);
        }
    }

    public static <T> FixtureSetUpResult<T> of(@Nullable T result) {
        return new FixtureSetUpResult<>(result, null);
    }

    public static <T> FixtureSetUpResult<T> of(@Nullable T result, @NotNull RuntimeException exception) {
        return new FixtureSetUpResult<>(result, exception);
    }

    public static <T> FixtureSetUpResult<T> exception(@NotNull RuntimeException exception) {
        return new FixtureSetUpResult<>(null, exception);
    }

    public @Nullable T getResult() {
        return result;
    }

    public @NotNull T getNotNullResult() {
        return Optional.ofNullable(result)
                .orElseThrow(() -> RequiredFixtureResultIsNull.exception(REQUIRED_FIXTURE_RESULT_IS_NULL.getMessage(), exception));
    }

    public Optional<Exception> getException() {
        return Optional.ofNullable(exception);
    }

    public FixtureSetUpResult<T> setExceptionProcessor(@NotNull Consumer<RuntimeException> exceptionProcessor) {
        Preconditions.notNull(exceptionProcessor, "Exception processor must not be null");
        this.exceptionProcessor = exceptionProcessor;
        return this;
    }

    public FixtureSetUpResult<T> process() {
        if (Objects.nonNull(exception)) {
            exceptionProcessor.accept(exception);
        }
        return this;
    }

}
