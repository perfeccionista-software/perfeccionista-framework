package io.perfeccionista.framework.value.number;

import io.perfeccionista.framework.value.checker.NumberChecker;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Set;
import java.util.function.UnaryOperator;

public abstract class AbstractNumberValue<T extends Number> implements NumberValue<T> {

    protected final NumberChecker<T> numberChecker;
    protected final Deque<UnaryOperator<T>> actualValueTransformers = new ArrayDeque<>();

    protected T rawActual;
    protected T processedActual;

    public AbstractNumberValue(NumberChecker<T> numberChecker) {
        this.numberChecker = numberChecker;
    }

    @Override
    public AbstractNumberValue<T> transformExpected(UnaryOperator<T> transformFunction) {
        numberChecker.setTransformers(Set.of(transformFunction));
        return this;
    }

    @Override
    public AbstractNumberValue<T> transformActual(UnaryOperator<T> transformFunction) {
        actualValueTransformers.clear();
        actualValueTransformers.addLast(transformFunction);
        return this;
    }

    @Override
    public AbstractNumberValue<T> withoutProcessing() {
        numberChecker.setProcessExpectedStatement(false);
        return this;
    }

    @Override
    public @NotNull T get() {
        return numberChecker.getProcessedExpected();
    }

    @Override
    public boolean check(@NotNull T actual) {
        rawActual = actual;
        processedActual = actual;
        for (UnaryOperator<T> transformer : actualValueTransformers) {
            processedActual = transformer.apply(processedActual);
        }
        return numberChecker.check(processedActual);
    }

    @Override
    public String toString() {
        return String.format("Number value: {checker = %s; rawExpected = '%s'; rawActual = '%s'}\n"
                        + "    processedExpected = '%s'\n"
                        + "      processedActual = '%s'",
                numberChecker.getClass().getCanonicalName(), numberChecker.getExpected(), rawActual,
                numberChecker.getProcessedExpected(),
                processedActual);
    }

}