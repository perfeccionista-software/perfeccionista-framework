package io.perfeccionista.framework.repeater;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.preconditions.Preconditions;
import io.perfeccionista.framework.repeater.policy.NoRepeatPolicy;
import io.perfeccionista.framework.repeater.policy.RepeatPolicy;
import io.perfeccionista.framework.service.Service;
import io.perfeccionista.framework.service.ServiceConfiguration;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class RepeatPolicyService implements Service {

    protected Environment environment = null;
    protected RepeatPolicyServiceConfiguration configuration = null;

    @Override
    public void init(@NotNull Environment environment) {
        Preconditions.notNull(environment, "Environment must not be null");
        this.environment = environment;
    }

    @Override
    public void init(@NotNull Environment environment, @NotNull ServiceConfiguration configuration) {
        Preconditions.notNull(environment, "Environment must not be null");
        Preconditions.notNull(configuration, "Service configuration must not be null");
        this.environment = environment;
        this.configuration = validate(configuration, RepeatPolicyServiceConfiguration.class);
    }

    public RepeatPolicy getRepeatPolicy() {
        if (Objects.isNull(configuration)) {
            return new NoRepeatPolicy();
        }
        return configuration.getRepeatPolicy();
    }

}
