package io.perfeccionista.framework.action.timeouts;

import org.jetbrains.annotations.NotNull;

import java.time.Duration;

/**
 * TODO: JavaDoc
 */
public class DefaultTimeouts implements Timeouts {

    @Override
    public @NotNull Duration getTimeout(@NotNull Class<? extends TimeoutsType> type) {
        if (LogicTimeout.class.equals(type)) {
            return Duration.ofSeconds(30L);
        } else if (CheckTimeout.class.equals(type)) {
            return Duration.ofSeconds(5L);
        } else if (LogicDelayTimeout.class.equals(type) || CheckDelayTimeout.class.equals(type)) {
            return Duration.ofMillis(50L);
        } else {
            return Duration.ofNanos(0);
        }
    }

}
