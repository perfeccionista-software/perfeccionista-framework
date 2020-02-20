package io.perfeccionista.framework.value.string.checker;

import org.jetbrains.annotations.NotNull;
import io.perfeccionista.framework.value.string.StringChecker;

public class StringSymbolsChecker implements StringChecker {

    @Override
    public boolean check(@NotNull String expected, @NotNull String actual) {
        return actual.equals(expected);
    }

    @Override
    public boolean isProcessExpectedStatement() {
        return false;
    }

}
