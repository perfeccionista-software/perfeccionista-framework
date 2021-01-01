package io.perfeccionista.framework.value.checker.object;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.value.checker.ObjectChecker;
import io.perfeccionista.framework.value.transformer.ValueTransformer;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayDeque;
import java.util.Deque;

public abstract class AbstractObjectChecker implements ObjectChecker {

    protected Deque<ValueTransformer<Object>> transformers = new ArrayDeque<>();
    protected Environment environment;

    protected Object actual;

    protected AbstractObjectChecker(@NotNull Environment environment) {
        this.environment = environment;
    }

    @Override
    public @NotNull Object getActual() {
        return actual;
    }

    @Override
    public @NotNull Object getProcessedActual() {
        return applyTransformersToActual(actual);
    }

    @Override
    public void setActual(@NotNull Object actual) {
        this.actual = actual;
    }

    @Override
    public void addTransformer(@NotNull ValueTransformer<Object> transformer) {
        transformers.addLast(transformer);
    }

    protected Object applyTransformersToActual(Object actual) {
        Object processedActual = actual;
        for (ValueTransformer<Object> transformer : transformers) {
            processedActual = transformer.transformActual(processedActual);
        }
        return processedActual;
    }

    protected Object applyTransformersToExpected(Object expected) {
        Object processedExpected = expected;
        for (ValueTransformer<Object> transformer : transformers) {
            processedExpected = transformer.transformExpected(processedExpected);
        }
        return processedExpected;
    }

}
