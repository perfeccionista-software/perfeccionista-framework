package io.perfeccionista.framework.value.checker.number;

import io.perfeccionista.framework.value.checker.AbstractNumberChecker;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

public class BigDecimalLessOrEqualsNumberChecker extends AbstractNumberChecker<BigDecimal> {

    private final BigDecimal expectedIntValue;

    public BigDecimalLessOrEqualsNumberChecker(@NotNull BigDecimal expectedIntValue) {
        this.expectedIntValue = expectedIntValue;
    }

    @Override
    public @NotNull BigDecimal getExpected() {
        return expectedIntValue;
    }

    @Override
    public @NotNull BigDecimal getProcessedExpected() {
        return applyTransformersToExpected(expectedIntValue);
    }

    @Override
    public boolean check() {
        return getProcessedActual().compareTo(getProcessedExpected()) <= 0;
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
                getExpected(), getActual(), this.getClass().getCanonicalName(), "less than or equals", getProcessedExpected(), getProcessedActual());
    }

}