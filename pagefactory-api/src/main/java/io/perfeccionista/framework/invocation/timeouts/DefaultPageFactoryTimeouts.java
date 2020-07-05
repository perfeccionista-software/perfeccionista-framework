package io.perfeccionista.framework.invocation.timeouts;

import org.jetbrains.annotations.NotNull;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class DefaultPageFactoryTimeouts implements Timeouts {

    private final Map<Class<? extends TimeoutsType>, Duration> timeouts = new HashMap<>();

    public DefaultPageFactoryTimeouts() {
        timeouts.put(LogicTimeout.class, Duration.ofSeconds(30L));
        timeouts.put(CheckTimeout.class, Duration.ofSeconds(5L));
        timeouts.put(LogicDelayTimeout.class, Duration.ofMillis(10L));
    }

    @Override
    public @NotNull Duration getTimeout(@NotNull Class<? extends TimeoutsType> timeoutType) {
        if (timeouts.containsKey(timeoutType)) {
            return timeouts.get(timeoutType);
        }
        return Duration.ofNanos(0);
    }

    @Override
    public DefaultPageFactoryTimeouts setTimeout(@NotNull Class<? extends TimeoutsType> timeoutType, @NotNull Duration duration) {
        timeouts.put(timeoutType, duration);
        return this;
    }

}
