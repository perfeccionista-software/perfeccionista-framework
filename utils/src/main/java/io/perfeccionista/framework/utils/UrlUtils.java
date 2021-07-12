package io.perfeccionista.framework.utils;

import org.jetbrains.annotations.NotNull;

import java.util.regex.Pattern;

import static java.util.regex.Pattern.DOTALL;

public class UrlUtils {
    private static final Pattern ABSOLUTE_URL_REGEX = Pattern.compile("^[a-zA-Z]+:.*", DOTALL);

    private UrlUtils() {
    }

    public static boolean isAbsoluteUrl(@NotNull String url) {
        return ABSOLUTE_URL_REGEX.matcher(url).find();
    }

    public static @NotNull String withoutFollowingSlash(@NotNull String url) {
        return url.endsWith("/") ? url.substring(0, url.length() - 1) : url;

    }

}
