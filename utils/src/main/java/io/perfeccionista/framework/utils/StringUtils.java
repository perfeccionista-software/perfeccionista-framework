package io.perfeccionista.framework.utils;

import io.perfeccionista.framework.exceptions.EmptyPath;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

import static io.perfeccionista.framework.exceptions.messages.UtilsMessages.EMPTY_PATH;
import static java.util.stream.Collectors.joining;

public class StringUtils {

    private StringUtils() {
    }

    public static boolean isBlank(@Nullable String string) {
        return (Objects.isNull(string) || string.trim().isEmpty());
    }

    public static boolean isNotBlank(@Nullable String string) {
        return !isBlank(string);
    }

    public static String toUpperCaseCharAfterBlanks(@NotNull String string) {
        boolean isPreviousBlank = true;
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            char currentChar = string.charAt(i);
            if(!Character.isLetterOrDigit(currentChar)) {
                result.append(currentChar);
                isPreviousBlank = true;
            } else {
                if (isPreviousBlank) {
                    result.append(Character.toUpperCase(currentChar));
                    isPreviousBlank = false;
                } else {
                    result.append(currentChar);
                }
            }
        }
        return result.toString();
    }

    public static String[] splitPathByArrow(@NotNull String path) {
        String trimmedElementPath = path.trim();
        if (isBlank(trimmedElementPath)) {
            throw EmptyPath.exception(EMPTY_PATH.getMessage());
        }
        return Arrays.stream(trimmedElementPath.split("\\s->\\s"))
                .map(String::trim)
                .toArray(String[]::new);
    }

    public static String indexesToString(@NotNull Collection<Integer> indexes) {
        return indexes.stream()
                .map(Object::toString)
                .collect(Collectors.joining(", "));
    }

    public static String objectsToString(@Nullable Object[] objects) {
        return Objects.isNull(objects) ? "null" : Arrays.stream(objects)
                .map(arg -> arg == null ? "null" : arg.toString())
                .collect(joining(","));
    }

    public static String objectTypesToString(@Nullable Object[] objects) {
        return Objects.isNull(objects) ? "null" : Arrays.stream(objects)
                .map(arg -> arg == null ? "null" : arg.getClass().getCanonicalName())
                .collect(joining(","));
    }

    public static String[] stringArray(String... stringArguments) {
        return stringArguments;
    }










}
