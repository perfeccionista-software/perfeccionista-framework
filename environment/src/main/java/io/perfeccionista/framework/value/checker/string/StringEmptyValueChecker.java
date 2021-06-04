package io.perfeccionista.framework.value.checker.string;

import io.perfeccionista.framework.Environment;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.utils.StringUtils.isBlank;

public class StringEmptyValueChecker extends AbstractStringChecker {

    public StringEmptyValueChecker(@NotNull Environment environment) {
        super(environment);
    }

    @Override
    public @NotNull String getExpected() {
        return "";
    }

    @Override
    public @NotNull String getProcessedExpected() {
        return applyTransformersToExpected("");
    }

    @Override
    public boolean check() {
        return isBlank(getProcessedActual());
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
                getExpected(), getActual(), this.getClass().getCanonicalName(), "empty", getProcessedExpected(), getProcessedActual());
    }

}
