package io.perfeccionista.framework.value.string.checker;

import org.jetbrains.annotations.NotNull;

public class StringLengthChecker extends AbstractStringChecker {

    private int expectedValue;

    public StringLengthChecker(int expectedValue) {
        this.expectedValue = expectedValue;
    }

    @Override
    public String getExpected() {
        return String.valueOf(expectedValue);
    }

    @Override
    public String getProcessedExpected() {
        return getExpected();
    }

    @Override
    public boolean check(@NotNull String actual) {
        return expectedValue == actual.length();
    }

}