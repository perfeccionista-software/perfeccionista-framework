package io.perfeccionista.framework.utils;

import io.perfeccionista.framework.exceptions.EmptyPath;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import static io.perfeccionista.framework.exceptions.messages.UtilsMessages.EMPTY_PATH;
import static org.junit.platform.commons.util.StringUtils.isBlank;

public class StringUtils {

    private StringUtils() {
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

}
