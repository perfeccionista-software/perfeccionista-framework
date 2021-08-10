package io.perfeccionista.framework.value.checker.number;

import org.jetbrains.annotations.NotNull;

public class IntegerEqualsNumberChecker extends AbstractNumberChecker<Integer> {

    private final int expectedIntValue;

    public IntegerEqualsNumberChecker(int expectedIntValue) {
        this.expectedIntValue = expectedIntValue;
    }

    @Override
    public @NotNull Integer getExpected() {
        return expectedIntValue;
    }

    @Override
    public @NotNull Integer getProcessedExpected() {
        return applyTransformersToExpected(expectedIntValue);
    }

    @Override
    public boolean check() {
        return getProcessedActual().equals(getProcessedExpected());
    }

    @Override
    public @NotNull String getShortComparisonDescription() {
        return String.format(" = %s", getProcessedExpected());
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
                getExpected(), getActual(), this.getClass().getCanonicalName(), "equals", getProcessedExpected(), getProcessedActual());
    }

}
