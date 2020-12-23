package io.perfeccionista.framework.value.checker.string;

import io.perfeccionista.framework.Environment;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class StringContainsAllChecker extends AbstractStringChecker {

    private final Set<String> expectedValues;

    public StringContainsAllChecker(@NotNull Environment environment, @NotNull Collection<String> expectedValues) {
        super(environment);
        this.expectedValues = Set.copyOf(expectedValues);
    }

    @Override
    public @NotNull String getExpected() {
        return '{' + String.join(";", expectedValues) + '}';
    }

    @Override
    public @NotNull String getProcessedExpected() {
        return '{' + expectedValues.stream()
                .map(this::processExpectedValue)
                .map(this::applyTransformersToExpected)
                .collect(Collectors.joining(";")) + '}';
    }

    @Override
    public boolean check() {
        return expectedValues.stream()
                .map(this::processExpectedValue)
                .map(this::applyTransformersToExpected)
                .allMatch(getProcessedActual()::contains);
    }

    @Override
    public @NotNull String getComparisonDescription() {
        return String.format("Compare parameters:\n"
                        + "          rawExpected: '%s'\n"
                        + "            rawActual: '%s'\n"
                        + "              checker: %s\n}"
                        + "               method: %s\n"
                        + "    processedExpected: '%s'\n"
                        + "      processedActual: '%s'",
                getExpected(), getActual(), this.getClass().getCanonicalName(), "contains all", getProcessedExpected(), getProcessedActual());
    }

}