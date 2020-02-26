package io.perfeccionista.framework.pagefactory.operations;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * Класс, предназначенный для возвращения результата операции, которая может
 * вызывать исключение. Использование такого класса дает возможность пользователю
 * по своему усмотрению обработать любой результат завершения операции,
 * включая исключение, возникшее в процессе выполнения.
 * @param <T> тип возвращаемого операцией значения
 * @see Optional
 */
public class OperationResult<T> {

    protected final boolean success;
    protected final T result;
    protected final PerfeccionistaException exception;

    protected OperationResult(boolean success, T result, PerfeccionistaException exception) {
        this.success = success;
        this.result = result;
        this.exception = exception;
    }

    public boolean isSuccess() {
        return success;
    }

    public T getResultOrThrow() {
        if (success) {
            return result;
        }
        throw exception;
    }

    public void throwIfNotSuccess() {
        if (!success && null != exception) {
            throw exception;
        }
    }

    public Optional<T> getResult() {
        return Optional.ofNullable(result);
    }

    public Optional<PerfeccionistaException> getException() {
        return Optional.ofNullable(exception);
    }

    public static <T> OperationResult<T> execute(@NotNull Supplier<T> supplier) {
        try {
            return success(supplier.get());
        } catch (PerfeccionistaException e) {
            return failure(e);
        }
    }

    public static OperationResult<Void> execute(@NotNull Runnable runnable) {
        try {
            runnable.run();
            return success(null);
        } catch (PerfeccionistaException e) {
            return failure(e);
        }
    }

    public static <T> OperationResult<T> success(@Nullable T result) {
        return new OperationResult<>(true, result, null);
    }

    public static <T> OperationResult<T> success(@Nullable T result,
                                                 @Nullable PerfeccionistaException exception) {
        return new OperationResult<>(true, result, exception);
    }

    public static <T> OperationResult<T> failure(@Nullable PerfeccionistaException exception) {
        return new OperationResult<>(false, null, exception);
    }

    public static <T> OperationResult<T> failure(@Nullable T result,
                                                 @Nullable PerfeccionistaException exception) {
        return new OperationResult<>(false, result, exception);
    }

}

