package io.perfeccionista.framework.repeater;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.exceptions.IncorrectServiceConfiguration;
import io.perfeccionista.framework.repeater.policy.NoRepeatPolicy;
import io.perfeccionista.framework.repeater.policy.RepeatPolicy;
import io.perfeccionista.framework.service.DefaultServiceConfiguration;
import io.perfeccionista.framework.service.Service;
import io.perfeccionista.framework.service.ServiceConfiguration;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.CHECK_CONFIGURATION_NOT_VALID;

@DefaultServiceConfiguration(DefaultRepeatPolicyServiceConfiguration.class)
public class RepeatPolicyService implements Service {

    protected Environment environment;
    protected RepeatPolicyServiceConfiguration configuration = null;

    @Override
    public void init(@NotNull Environment environment, @NotNull ServiceConfiguration configuration) {
        this.environment = environment;
        this.configuration = validate(configuration);
    }

    public RepeatPolicy getRepeatPolicy() {
        if (Objects.isNull(configuration)) {
            return new NoRepeatPolicy();
        }
        return configuration.getRepeatPolicy();
    }

    protected RepeatPolicyServiceConfiguration validate(ServiceConfiguration configuration) {
        if (configuration instanceof RepeatPolicyServiceConfiguration) {
            return (RepeatPolicyServiceConfiguration) configuration;
        }
        throw IncorrectServiceConfiguration.exception(
                CHECK_CONFIGURATION_NOT_VALID.getMessage(configuration.getClass().getCanonicalName(), this.getClass().getCanonicalName()));
    }

}
