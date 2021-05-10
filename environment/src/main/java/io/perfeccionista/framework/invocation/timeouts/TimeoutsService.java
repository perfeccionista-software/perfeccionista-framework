package io.perfeccionista.framework.invocation.timeouts;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.exceptions.IncorrectServiceConfiguration;
import io.perfeccionista.framework.invocation.timeouts.type.TimeoutsType;
import io.perfeccionista.framework.service.DefaultServiceConfiguration;
import io.perfeccionista.framework.service.Service;
import io.perfeccionista.framework.service.ServiceConfiguration;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.SERVICE_CONFIGURATION_NOT_VALID;

@DefaultServiceConfiguration(DefaultTimeoutsServiceConfiguration.class)
public class TimeoutsService implements Service {

    protected TimeoutsServiceConfiguration configuration = null;

    protected final Map<Class<? extends TimeoutsType>, Duration> timeouts = new HashMap<>();

    @Override
    public void init(@NotNull Environment environment, @NotNull ServiceConfiguration configuration) {
        this.configuration = validate(configuration);
        this.configuration.getTimeouts().forEach(timeouts::put);
    }

    public @NotNull Duration getTimeout(@NotNull Class<? extends TimeoutsType> timeoutType) {
        if (timeouts.containsKey(timeoutType)) {
            return timeouts.get(timeoutType);
        }
        return Duration.ofNanos(0);
    }

    public @NotNull TimeoutsService setTimeout(@NotNull Class<? extends TimeoutsType> timeoutType, @NotNull Duration duration) {
        timeouts.put(timeoutType, duration);
        return this;
    }

    protected TimeoutsServiceConfiguration validate(ServiceConfiguration configuration) {
        if (configuration instanceof TimeoutsServiceConfiguration) {
            return (TimeoutsServiceConfiguration) configuration;
        }
        throw IncorrectServiceConfiguration.exception(
                SERVICE_CONFIGURATION_NOT_VALID.getMessage(configuration.getClass().getCanonicalName(), this.getClass().getCanonicalName()));
    }

}
