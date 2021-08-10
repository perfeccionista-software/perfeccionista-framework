package io.perfeccionista.framework.value.checker.number;

import io.perfeccionista.framework.value.checker.NumberChecker;
import io.perfeccionista.framework.value.transformer.ValueTransformer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;

public abstract class AbstractNumberChecker<T extends Number> implements NumberChecker<T> {

    protected Deque<ValueTransformer<T>> transformers = new ArrayDeque<>();

    protected T actual;

    @Override
    public @Nullable T getActual() {
        return actual;
    }

    @Override
    public @Nullable T getProcessedActual() {
        return Objects.isNull(actual) ? null : applyTransformersToActual(actual);
    }

    @Override
    public void setActual(@NotNull T actual) {
        this.actual = actual;
    }

    @Override
    public void addTransformer(@NotNull ValueTransformer<T> transformer) {
        transformers.addLast(transformer);
    }

    protected T applyTransformersToActual(@NotNull T actual) {
        T processedActual = actual;
        for (ValueTransformer<T> transformer : transformers) {
            processedActual = transformer.transformActual(processedActual);
        }
        return processedActual;
    }

    protected T applyTransformersToExpected(@NotNull T expected) {
        T processedExpected = expected;
        for (ValueTransformer<T> transformer : transformers) {
            processedExpected = transformer.transformExpected(processedExpected);
        }
        return processedExpected;
    }

}
