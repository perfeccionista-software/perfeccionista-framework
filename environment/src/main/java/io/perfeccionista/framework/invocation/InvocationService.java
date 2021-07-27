package io.perfeccionista.framework.invocation;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.exceptions.IncorrectServiceConfiguration;
import io.perfeccionista.framework.invocation.runner.EmptyInvocationRunner;
import io.perfeccionista.framework.invocation.runner.InvocationInfoNameFormatter;
import io.perfeccionista.framework.invocation.runner.InvocationInfoStatisticsFormatter;
import io.perfeccionista.framework.invocation.runner.InvocationRunner;
import io.perfeccionista.framework.service.DefaultServiceConfiguration;
import io.perfeccionista.framework.service.Service;
import io.perfeccionista.framework.service.ServiceConfiguration;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.SERVICE_CONFIGURATION_NOT_VALID;

@DefaultServiceConfiguration(DefaultInvocationServiceConfiguration.class)
public class InvocationService implements Service {

    protected Environment environment;
    protected InvocationServiceConfiguration configuration = null;

    @Override
    public void init(@NotNull Environment environment, @NotNull ServiceConfiguration configuration) {
        this.environment = environment;
        this.configuration = validate(configuration);
    }

    public @NotNull Class<? extends InvocationRunner> getInvocationRunnerImplementation(@NotNull Class<?> invocationWrapper) {
        return configuration == null
                ? EmptyInvocationRunner.class
                : configuration.getInvocationRunnerImplementation(invocationWrapper);
    }

    public InvocationInfoNameFormatter getInvocationInfoNameFormatter() {
        return configuration.getInvocationInfoNameFormatter();
    }

    public InvocationInfoStatisticsFormatter getInvocationInfoStatisticsFormatter() {
        return configuration.getInvocationInfoStatisticsFormatter();
    }

    protected InvocationServiceConfiguration validate(ServiceConfiguration configuration) {
        if (configuration instanceof InvocationServiceConfiguration) {
            return (InvocationServiceConfiguration) configuration;
        }
        throw IncorrectServiceConfiguration.exception(
                SERVICE_CONFIGURATION_NOT_VALID.getMessage(configuration.getClass().getCanonicalName(), this.getClass().getCanonicalName()));
    }

}
