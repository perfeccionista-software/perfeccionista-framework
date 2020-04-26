package io.perfeccionista.framework.value.checker.stringvalue;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.value.processor.ValueExpressionProcessor;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class StringContainsAnyChecker extends AbstractStringChecker {

    private Environment environment;
    private Set<String> expectedValues;

    public StringContainsAnyChecker(Environment environment, Collection<String> expectedValues) {
        this.environment = environment;
        this.expectedValues = Set.copyOf(expectedValues);
    }

    @Override
    public String getExpected() {
        return '{' + String.join(";", expectedValues) + '}';
    }

    @Override
    public String getProcessedExpected() {
        return '{' + expectedValues.stream()
                .map(this::processExpectedValue)
                .map(this::applyTransformers)
                .collect(Collectors.joining(";")) + '}';
    }

    @Override
    public boolean check(@NotNull String actual) {
        return expectedValues.stream()
                .map(this::processExpectedValue)
                .map(this::applyTransformers)
                .anyMatch(actual::contains);
    }

    protected String processExpectedValue(String valueExpression) {
        if (isProcessExpectedStatement()) {
            return new ValueExpressionProcessor(environment).processExpression(valueExpression).toString();
        }
        return valueExpression;
    }

}
