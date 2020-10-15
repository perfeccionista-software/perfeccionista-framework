package io.perfeccionista.framework.utils;

import org.jetbrains.annotations.NotNull;

import java.time.Duration;

/**
 * Утилиты связанные с работой потоков, ожиданиями.
 */
public final class ThreadUtils {

    private ThreadUtils() {
    }

    /**
     * Утилитный метод, используемый для явного ожидания.
     * В общем случае, его использование является плохой практикой.
     * @param duration продолжительность ожидания
     */
    public static void sleep(@NotNull Duration duration) {
        if (duration.isZero()) {
            return;
        }
        try {
            Thread.sleep(duration.toMillis());
        } catch (InterruptedException expected) {
            /* do nothing */
        }
    }

}
