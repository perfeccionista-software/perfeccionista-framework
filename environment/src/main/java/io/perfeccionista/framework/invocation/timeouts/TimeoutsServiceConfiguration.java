package io.perfeccionista.framework.invocation.timeouts;

import io.perfeccionista.framework.invocation.timeouts.type.TimeoutsType;
import io.perfeccionista.framework.service.ServiceConfiguration;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;
import java.util.Map;

public interface TimeoutsServiceConfiguration extends ServiceConfiguration {

    @NotNull Map<Class<? extends TimeoutsType>, Duration> getTimeouts();

}
