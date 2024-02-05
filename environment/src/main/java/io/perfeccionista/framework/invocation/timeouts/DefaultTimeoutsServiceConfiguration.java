package io.perfeccionista.framework.invocation.timeouts;

import io.perfeccionista.framework.invocation.timeouts.type.RepeatInvocationDelayTimeout;
import io.perfeccionista.framework.invocation.timeouts.type.RepeatInvocationTimeout;
import io.perfeccionista.framework.invocation.timeouts.type.RunInvocationTimeout;
import io.perfeccionista.framework.invocation.timeouts.type.TimeoutsType;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;
import java.util.Map;


// TODO: Добавить возможность переопределять значения из проперти или переменных окружения
public class DefaultTimeoutsServiceConfiguration implements TimeoutsServiceConfiguration {

    @Override
    public @NotNull Map<Class<? extends TimeoutsType>, Duration> getTimeouts() {
        return Map.of(
                RepeatInvocationTimeout.class, Duration.ofSeconds(5L),
                RepeatInvocationDelayTimeout.class, Duration.ofMillis(10L),
                RunInvocationTimeout.class, Duration.ofSeconds(20L)
        );
    }

}
