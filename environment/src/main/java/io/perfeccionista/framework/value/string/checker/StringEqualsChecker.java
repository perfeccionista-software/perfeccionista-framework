package io.perfeccionista.framework.value.string.checker;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.value.processor.ValueExpressionProcessor;
import org.jetbrains.annotations.NotNull;

public class StringEqualsChecker extends AbstractStringChecker {

    private Environment environment;
    private String expectedValue;

    public StringEqualsChecker(Environment environment, String expectedValue) {
        this.environment = environment;
        this.expectedValue = expectedValue;
    }

    @Override
    public String getExpected() {
        return expectedValue;
    }

    @Override
    public String getProcessedExpected() {
        return applyTransformers(processExpectedValue(expectedValue));
    }

    @Override
    public boolean check(@NotNull String actual) {
        return actual.equals(getProcessedExpected());
    }

    protected String processExpectedValue(String valueExpression) {
        if (isProcessExpectedStatement()) {
            return new ValueExpressionProcessor(environment).processExpression(valueExpression).toString();
        }
        return valueExpression;
    }

}

