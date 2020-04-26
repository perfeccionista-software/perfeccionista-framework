package io.perfeccionista.framework.value.checker.stringvalue;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.value.processor.ValueExpressionProcessor;
import org.jetbrains.annotations.NotNull;

public class StringContainsIgnoreCaseChecker extends AbstractStringChecker {

    private Environment environment;
    private String expectedValue;

    public StringContainsIgnoreCaseChecker(Environment environment, String expectedValue) {
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
        return actual.toLowerCase().contains(getProcessedExpected().toLowerCase());
    }

    protected String processExpectedValue(String valueExpression) {
        if (isProcessExpectedStatement()) {
            return new ValueExpressionProcessor(environment).processExpression(valueExpression).toString();
        }
        return valueExpression;
    }

}
