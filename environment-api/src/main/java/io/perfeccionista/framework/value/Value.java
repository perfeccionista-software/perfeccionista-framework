package io.perfeccionista.framework.value;

import org.jetbrains.annotations.NotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface Value<T> {
    Pattern CONDITIONS_PATTERN = Pattern.compile("^\\((?<conditions>.*?)\\)");

    @NotNull T get();

    boolean check(@NotNull T actual);

    // TODO: На эту логику нужны тесты для каждого кейса
    default @NotNull ValueDeclaration parseRawExpected(String rawExpected) {
        Matcher conditionsMatcher = CONDITIONS_PATTERN.matcher(rawExpected);
        if (conditionsMatcher.find()) {
            return ValueDeclaration.of(
                    rawExpected.replaceFirst(CONDITIONS_PATTERN.pattern(), ""),
                    conditionsMatcher.group("conditions"),
                    conditionsMatcher.group()
            );
        } else {
            if ("\\".equals(rawExpected.substring(0, 1))) {
                return ValueDeclaration.of(rawExpected.substring(1));
            } else {
                return ValueDeclaration.of(rawExpected);
            }
        }
    }

}
