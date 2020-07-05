package io.perfeccionista.framework.invocation.timeouts;

import org.jetbrains.annotations.NotNull;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * TODO: JavaDoc
 */
public class DefaultTimeouts implements Timeouts {

    private final Map<Class<? extends TimeoutsType>, Duration> timeouts = new HashMap<>();

    @Override
    public @NotNull Duration getTimeout(@NotNull Class<? extends TimeoutsType> timeoutType) {
        if (timeouts.containsKey(timeoutType)) {
            return timeouts.get(timeoutType);
        }
        return Duration.ofNanos(0);
    }

    @Override
    public DefaultTimeouts setTimeout(@NotNull Class<? extends TimeoutsType> timeoutType, @NotNull Duration duration) {
        timeouts.put(timeoutType, duration);
        return this;
    }

}
