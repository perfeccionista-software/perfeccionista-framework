package io.perfeccionista.framework.value.checker.number;

import io.perfeccionista.framework.value.checker.AbstractNumberChecker;
import org.jetbrains.annotations.NotNull;

public class DoubleGreaterOrEqualsNumberChecker extends AbstractNumberChecker<Double> {

    private final Double expectedIntValue;

    public DoubleGreaterOrEqualsNumberChecker(@NotNull Double expectedIntValue) {
        this.expectedIntValue = expectedIntValue;
    }

    @Override
    public @NotNull Double getExpected() {
        return expectedIntValue;
    }

    @Override
    public @NotNull Double getProcessedExpected() {
        return applyTransformersToExpected(expectedIntValue);
    }

    @Override
    public boolean check() {
        return getProcessedActual().compareTo(getProcessedExpected()) >= 0;
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
                getExpected(), getActual(), this.getClass().getCanonicalName(), "greater than or equals", getProcessedExpected(), getProcessedActual());
    }

}