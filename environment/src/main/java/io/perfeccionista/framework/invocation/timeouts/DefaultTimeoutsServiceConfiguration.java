package io.perfeccionista.framework.invocation.timeouts;

import io.perfeccionista.framework.invocation.timeouts.type.CheckDelayTimeout;
import io.perfeccionista.framework.invocation.timeouts.type.CheckTimeout;
import io.perfeccionista.framework.invocation.timeouts.type.LogicDelayTimeout;
import io.perfeccionista.framework.invocation.timeouts.type.LogicTimeout;
import io.perfeccionista.framework.invocation.timeouts.type.TimeoutsType;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// TODO: Добавить возможность переопределять значения из проперти или переменных окружения
public class DefaultTimeoutsServiceConfiguration implements TimeoutsServiceConfiguration {

    @Override
    public @NotNull Map<Class<? extends TimeoutsType>, Duration> getTimeouts() {
        return Stream.of(
                Map.entry(CheckTimeout.class, Duration.ofSeconds(7L)),
                Map.entry(CheckDelayTimeout.class, Duration.ofMillis(10L)),
                Map.entry(LogicTimeout.class, Duration.ofSeconds(30L)),
                Map.entry(LogicDelayTimeout.class, Duration.ofMillis(10L))
        ).collect(Collectors.toMap(Entry::getKey, Entry::getValue));
    }

}
