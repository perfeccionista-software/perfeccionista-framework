package io.perfeccionista.framework.invocation.timeouts;

import org.jetbrains.annotations.NotNull;

import java.time.Duration;

/**
 * TODO: JavaDoc
 */
public class DefaultTimeouts implements Timeouts {

    @Override
    public @NotNull Duration getTimeout(@NotNull Class<? extends TimeoutsType> type) {
        return Duration.ofNanos(0);
    }

}
