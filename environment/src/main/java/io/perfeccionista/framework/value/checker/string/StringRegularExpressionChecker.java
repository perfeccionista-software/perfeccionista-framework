package io.perfeccionista.framework.value.checker.string;

import io.perfeccionista.framework.Environment;
import org.intellij.lang.annotations.Language;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringRegularExpressionChecker extends AbstractStringChecker {

    private final String expectedValue;

    public StringRegularExpressionChecker(@NotNull Environment environment, @NotNull @Language("RegExp") String expectedValue) {
        super(environment);
        this.expectedValue = expectedValue;
        this.processExpectedStatement = false;
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
        Pattern regexpPattern = Pattern.compile(getProcessedExpected());
        Matcher regexpMatcher = regexpPattern.matcher(getProcessedActual());
        return regexpMatcher.find();
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
                getExpected(), getActual(), this.getClass().getCanonicalName(), "regular expression", getProcessedExpected(), getProcessedActual());
    }

}
