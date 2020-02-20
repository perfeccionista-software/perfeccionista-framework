package io.perfeccionista.framework.value.string.checker;

import org.jetbrains.annotations.NotNull;
import io.perfeccionista.framework.value.string.StringChecker;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringRegularExpressionChecker implements StringChecker {

    @Override
    public boolean check(@NotNull String expected, @NotNull String actual) {
        Pattern regexpPattern = Pattern.compile(expected.trim());
        Matcher regexpMatcher = regexpPattern.matcher(actual);
        return regexpMatcher.find();
    }

}
