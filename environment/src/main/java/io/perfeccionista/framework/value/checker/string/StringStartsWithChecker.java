package io.perfeccionista.framework.value.checker.string;

import io.perfeccionista.framework.Environment;
import org.jetbrains.annotations.NotNull;

public class StringStartsWithChecker extends AbstractStringChecker {

    private final String expectedValue;

    public StringStartsWithChecker(@NotNull Environment environment, @NotNull String expectedValue) {
        super(environment);
        this.expectedValue = expectedValue;
    }

    @Override
    public @NotNull String getExpected() {
        return expectedValue;
    }

    @Override
    public @NotNull String getProcessedExpected() {
        return applyTransformersToExpected(processExpectedValue(expectedValue));
    }

    @Override
    public boolean check() {
        return getProcessedActual().startsWith(getProcessedExpected());
    }

    @Override
    public @NotNull String getComparisonDescription() {
        return String.format("Compare parameters:\n"
                        + "          rawExpected: '%s'\n"
                        + "            rawActual: '%s'\n"
                        + "              checker: %s\n"
                        + "               method: %s\n"
                        + "    processedExpected: '%s'\n"
                        + "      processedActual: '%s'",
                getExpected(), getActual(), this.getClass().getCanonicalName(), "starts with", getProcessedExpected(), getProcessedActual());
    }

}
