package io.perfeccionista.framework.value.string;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.value.ValueDeclaration;
import io.perfeccionista.framework.value.processor.ValueExpressionProcessor;

import java.util.Arrays;
import java.util.Deque;

public abstract class AbstractStringValue implements StringValue {

    protected final String rawExpected;
    protected final ValueDeclaration valueDeclaration;
    protected final StringChecker stringChecker;
    protected final Deque<StringTransformer> transformers;
    protected final Environment environment;

    protected String processedExpected;
    protected String rawActual;
    protected String processedActual;

    protected AbstractStringValue(Environment environment, String expected) {
        this.environment = environment;
        this.rawExpected = expected;
        this.valueDeclaration = parseRawExpected(expected);
        this.stringChecker = resolveChecker(valueDeclaration);
        this.transformers = resolveTransformers(valueDeclaration);
    }

    @Override
    public void setStringTransformers(@Nullable StringTransformer... stringTransformers) {
        transformers.clear();
        if (null != stringTransformers) {
            transformers.addAll(Arrays.asList(stringTransformers));
        }
    }

    @Override
    public @NotNull String get() {
        processedExpected = valueDeclaration.getValueExpression();
        if (stringChecker.isProcessExpectedStatement()) {
            Object processedValue = processExpectedStatement(processedExpected);
            processedExpected = processedValue.toString();
        }
        for (StringTransformer transformer : transformers) {
            processedExpected = transformer.transformExpected(processedExpected);
        }
        return processedExpected;
    }

    @Override
    public boolean check(@NotNull String actual) {
        rawActual = actual;
        processedActual = actual;
        processedExpected = valueDeclaration.getValueExpression();
        if (stringChecker.isProcessExpectedStatement()) {
            Object processedValue = processExpectedStatement(processedExpected);
            processedExpected = processedValue.toString();
        }
        for (StringTransformer transformer : transformers) {
            processedExpected = transformer.transformExpected(processedExpected);
            processedActual = transformer.transformActual(processedActual);
        }
        return stringChecker.check(processedExpected, processedActual);
    }

    protected Object processExpectedStatement(String valueExpression) {
        return new ValueExpressionProcessor(environment).processExpression(valueExpression);
    }

    protected abstract @NotNull StringChecker resolveChecker(ValueDeclaration valueDeclaration);

    protected abstract Deque<StringTransformer> resolveTransformers(ValueDeclaration valueDeclaration);

    @Override
    public String toString() {
        return String.format("String value: {conditions = %s; rawExpected = '%s'; rawActual = '%s'}\n"
                        + "    processedExpected = '%s'\n"
                        + "      processedActual = '%s'",
                valueDeclaration.getValueCondition().orElse("none"), rawExpected, rawActual, processedExpected, processedActual);
    }

}
