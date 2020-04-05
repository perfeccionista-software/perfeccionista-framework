package io.perfeccionista.framework.value.number;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.UnaryOperator;

public abstract class AbstractNumberChecker<T extends Number> implements NumberChecker<T> {

    protected boolean processExpectedStatement = true;
    protected Set<UnaryOperator<T>> transformers = new HashSet<>();

    @Override
    public boolean isProcessExpectedStatement() {
        return this.processExpectedStatement;
    }

    @Override
    public void setProcessExpectedStatement(boolean processExpectedStatement) {
        this.processExpectedStatement = processExpectedStatement;
    }

    @Override
    public void setTransformers(Collection<UnaryOperator<T>> transformers) {
        this.transformers = Set.copyOf(transformers);
    }

    protected T applyTransformers(T value) {
        T processedValue = value;
        for (UnaryOperator<T> transformer : transformers) {
            processedValue = transformer.apply(processedValue);
        }
        return processedValue;
    }

}
