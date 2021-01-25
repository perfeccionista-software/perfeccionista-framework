package io.perfeccionista.framework.value.checker.string;

import io.perfeccionista.framework.Environment;
import org.jetbrains.annotations.NotNull;

public class StringLengthChecker extends AbstractStringChecker {

    private final int expectedValue;

    public StringLengthChecker(@NotNull Environment environment, int expectedValue) {
        super(environment);
        this.expectedValue = expectedValue;
    }

    @Override
    public @NotNull String getExpected() {
        return String.valueOf(expectedValue);
    }

    @Override
    public @NotNull String getProcessedExpected() {
        return getExpected();
    }

    @Override
    public boolean check() {
        return expectedValue == actual.length();
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
                getExpected(), getActual(), this.getClass().getCanonicalName(), "string length", getProcessedExpected(), getProcessedActual());
    }

}
