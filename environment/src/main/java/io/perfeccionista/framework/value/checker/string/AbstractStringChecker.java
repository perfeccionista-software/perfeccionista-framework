package io.perfeccionista.framework.value.checker.string;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.value.checker.StringChecker;
import io.perfeccionista.framework.value.processor.ValueExpressionProcessor;
import io.perfeccionista.framework.value.transformer.ValueTransformer;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayDeque;
import java.util.Deque;

public abstract class AbstractStringChecker implements StringChecker {

    protected Deque<ValueTransformer<String>> transformers = new ArrayDeque<>();
    protected boolean processExpectedStatement = true;
    protected Environment environment;

    protected String actual;

    public AbstractStringChecker(@NotNull Environment environment) {
        this.environment = environment;
    }

    @Override
    public @NotNull String getActual() {
        return actual;
    }

    @Override
    public @NotNull String getProcessedActual() {
        return applyTransformersToActual(actual);
    }

    @Override
    public void setActual(@NotNull String actual) {
        this.actual = actual;
    }

    @Override
    public void addTransformer(@NotNull ValueTransformer<String> transformer) {
        transformers.addLast(transformer);
    }

    @Override
    public void setProcessExpectedStatement(boolean processExpectedStatement) {
        this.processExpectedStatement = processExpectedStatement;
    }

    @Override
    public boolean isProcessExpectedStatement() {
        return this.processExpectedStatement;
    }

    protected String applyTransformersToActual(String actual) {
        String processedActual = actual;
        for (ValueTransformer<String> transformer : transformers) {
            processedActual = transformer.transformActual(processedActual);
        }
        return processedActual;
    }

    protected String applyTransformersToExpected(String expected) {
        String processedExpected = expected;
        for (ValueTransformer<String> transformer : transformers) {
            processedExpected = transformer.transformExpected(processedExpected);
        }
        return processedExpected;
    }

    protected String processExpectedValue(String valueExpression) {
        if (isProcessExpectedStatement()) {
            return new ValueExpressionProcessor(environment).processExpression(valueExpression).toString();
        }
        return valueExpression;
    }

}
