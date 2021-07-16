package io.perfeccionista.framework.value.checker.object;

import io.perfeccionista.framework.Environment;
import org.jetbrains.annotations.NotNull;

public class ObjectEqualsChecker extends AbstractObjectChecker {

    private final Object expectedValue;

    public ObjectEqualsChecker(@NotNull Environment environment, @NotNull Object expectedValue) {
        super(environment);
        this.expectedValue = expectedValue;
    }

    @Override
    public @NotNull Object getExpected() {
        return expectedValue;
    }

    @Override
    public @NotNull Object getProcessedExpected() {
        return applyTransformersToExpected(expectedValue);
    }

    @Override
    public boolean check() {
        return getProcessedActual().equals(getProcessedExpected());
    }

    @Override
    public @NotNull String getShortComparisonDescription() {
        return String.format("equals {%s}", getProcessedExpected());
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
