package io.perfeccionista.framework.value.checker.stringvalue;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.value.processor.ValueExpressionProcessor;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringRegularExpressionChecker extends AbstractStringChecker {

    private Environment environment;
    private String expectedValue;

    public StringRegularExpressionChecker(Environment environment, String expectedValue) {
        this.environment = environment;
        this.expectedValue = expectedValue;
        this.processExpectedStatement = false;
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
        Pattern regexpPattern = Pattern.compile(getProcessedExpected());
        Matcher regexpMatcher = regexpPattern.matcher(actual);
        return regexpMatcher.find();
    }

    protected String processExpectedValue(String valueExpression) {
        if (isProcessExpectedStatement()) {
            return new ValueExpressionProcessor(environment).processExpression(valueExpression).toString();
        }
        return valueExpression;
    }

}
