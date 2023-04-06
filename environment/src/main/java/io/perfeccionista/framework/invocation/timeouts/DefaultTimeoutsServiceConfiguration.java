package io.perfeccionista.framework.invocation.timeouts;

import io.perfeccionista.framework.invocation.timeouts.type.RepeatInvocationDelayTimeout;
import io.perfeccionista.framework.invocation.timeouts.type.RepeatInvocationTimeout;
import io.perfeccionista.framework.invocation.timeouts.type.RunInvocationTimeout;
import io.perfeccionista.framework.invocation.timeouts.type.TimeoutsType;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// TODO: Добавить возможность переопределять значения из проперти или переменных окружения
public class DefaultTimeoutsServiceConfiguration implements TimeoutsServiceConfiguration {

    @Override
    public @NotNull Map<Class<? extends TimeoutsType>, Duration> getTimeouts() {
        return Stream.of(
                new SimpleEntry<>(RepeatInvocationTimeout.class, Duration.ofSeconds(7L)),
                new SimpleEntry<>(RepeatInvocationDelayTimeout.class, Duration.ofMillis(10L)),
                new SimpleEntry<>(RunInvocationTimeout.class, Duration.ofSeconds(30L))
        ).collect(Collectors.toMap(Entry::getKey, Entry::getValue));
    }

}
