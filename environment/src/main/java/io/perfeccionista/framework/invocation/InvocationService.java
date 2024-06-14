package io.perfeccionista.framework.invocation;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.invocation.runner.EmptyInvocationRunner;
import io.perfeccionista.framework.invocation.runner.InvocationInfoNameFormatter;
import io.perfeccionista.framework.invocation.runner.InvocationInfoStatisticsFormatter;
import io.perfeccionista.framework.invocation.runner.InvocationRunner;
import io.perfeccionista.framework.preconditions.Preconditions;
import io.perfeccionista.framework.service.DefaultServiceConfiguration;
import io.perfeccionista.framework.service.Service;
import io.perfeccionista.framework.service.ServiceConfiguration;
import org.jetbrains.annotations.NotNull;

@DefaultServiceConfiguration(DefaultInvocationServiceConfiguration.class)
public class InvocationService implements Service {

    protected Environment environment;
    protected InvocationServiceConfiguration configuration = null;

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
        this.configuration = validate(configuration, InvocationServiceConfiguration.class);
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

}
