package io.perfeccionista.framework.value.string.checker;

import org.jetbrains.annotations.NotNull;
import io.perfeccionista.framework.value.string.StringChecker;

public class StringLengthChecker implements StringChecker {

    @Override
    public boolean check(@NotNull String expected, @NotNull String actual) {
        return Integer.parseInt(expected) == actual.length();
    }

}
