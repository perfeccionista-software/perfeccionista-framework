package io.perfeccionista.framework.value.checker;

import io.perfeccionista.framework.value.transformer.ValueTransformer;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayDeque;
import java.util.Deque;

public abstract class AbstractNumberChecker<T extends Number> implements NumberChecker<T> {

    protected Deque<ValueTransformer<T>> transformers = new ArrayDeque<>();

    protected T actual;

    @Override
    public @NotNull T getActual() {
        return actual;
    }

    @Override
    public @NotNull T getProcessedActual() {
        return applyTransformersToActual(actual);
    }

    @Override
    public void setActual(@NotNull T actual) {
        this.actual = actual;
    }

    @Override
    public void addTransformer(@NotNull ValueTransformer<T> transformer) {
        transformers.addLast(transformer);
    }

    protected T applyTransformersToActual(T actual) {
        T processedActual = actual;
        for (ValueTransformer<T> transformer : transformers) {
            processedActual = transformer.transformActual(processedActual);
        }
        return processedActual;
    }

    protected T applyTransformersToExpected(T expected) {
        T processedExpected = expected;
        for (ValueTransformer<T> transformer : transformers) {
            processedExpected = transformer.transformExpected(processedExpected);
        }
        return processedExpected;
    }

}
