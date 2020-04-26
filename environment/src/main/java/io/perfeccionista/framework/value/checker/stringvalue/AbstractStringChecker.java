package io.perfeccionista.framework.value.checker.stringvalue;

import io.perfeccionista.framework.value.checker.StringChecker;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.UnaryOperator;

public abstract class AbstractStringChecker implements StringChecker {

    protected boolean processExpectedStatement = true;
    protected Set<UnaryOperator<String>> transformers = new HashSet<>();

    @Override
    public boolean isProcessExpectedStatement() {
        return this.processExpectedStatement;
    }

    @Override
    public void setProcessExpectedStatement(boolean processExpectedStatement) {
        this.processExpectedStatement = processExpectedStatement;
    }

    @Override
    public void setTransformers(Collection<UnaryOperator<String>> transformers) {
        this.transformers = Set.copyOf(transformers);
    }

    protected String applyTransformers(String value) {
        String processedValue = value;
        for (UnaryOperator<String> transformer : transformers) {
            processedValue = transformer.apply(processedValue);
        }
        return processedValue;
    }

}
