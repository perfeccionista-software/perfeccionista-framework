package io.perfeccionista.framework.value.checker.number;

import io.perfeccionista.framework.value.checker.AbstractNumberChecker;
import org.jetbrains.annotations.NotNull;

public class DoubleNotEqualsNumberChecker extends AbstractNumberChecker<Double> {

    private final Double expectedIntValue;

    public DoubleNotEqualsNumberChecker(@NotNull Double expectedIntValue) {
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
        return !getProcessedActual().equals(getProcessedExpected());
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
                getExpected(), getActual(), this.getClass().getCanonicalName(), "not equals", getProcessedExpected(), getProcessedActual());
    }

}