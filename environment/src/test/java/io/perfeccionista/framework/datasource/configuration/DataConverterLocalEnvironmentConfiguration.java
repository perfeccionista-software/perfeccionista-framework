package io.perfeccionista.framework.datasource.configuration;

import org.jetbrains.annotations.NotNull;
import io.perfeccionista.framework.EnvironmentConfiguration;
import io.perfeccionista.framework.invocation.runner.InvocationRunnerConfiguration;
import io.perfeccionista.framework.invocation.timeouts.DefaultTimeouts;
import io.perfeccionista.framework.invocation.timeouts.Timeouts;
import io.perfeccionista.framework.datasource.DataConverterService;
import io.perfeccionista.framework.repeater.RepeatPolicy;
import io.perfeccionista.framework.service.UseService;

import static org.mockito.Mockito.mock;

@UseService(service = DataConverterService.class, configuration = TestDataConverterServiceConfiguration.class)
public class DataConverterLocalEnvironmentConfiguration implements EnvironmentConfiguration {

    private final InvocationRunnerConfiguration actionRunnerConfiguration;
    private final RepeatPolicy repeatPolicy;
    private final Timeouts timeouts;

    public DataConverterLocalEnvironmentConfiguration() {
        this.actionRunnerConfiguration = mock(InvocationRunnerConfiguration.class);
        this.repeatPolicy = mock(RepeatPolicy.class);
        this.timeouts = new DefaultTimeouts();
    }

    @Override
    public @NotNull InvocationRunnerConfiguration getInvocationRunnerConfiguration() {
        return actionRunnerConfiguration;
    }

    @Override
    public @NotNull RepeatPolicy getRepeatPolicy() {
        return repeatPolicy;
    }

    @Override
    public @NotNull Timeouts getTimeouts() {
        return timeouts;
    }

}
