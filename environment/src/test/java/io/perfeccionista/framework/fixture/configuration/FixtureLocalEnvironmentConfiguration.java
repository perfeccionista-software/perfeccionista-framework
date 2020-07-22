package io.perfeccionista.framework.fixture.configuration;

import io.perfeccionista.framework.EnvironmentConfiguration;
import io.perfeccionista.framework.fixture.FixtureService;
import io.perfeccionista.framework.invocation.runner.InvocationRunnerConfiguration;
import io.perfeccionista.framework.invocation.timeouts.DefaultTimeouts;
import io.perfeccionista.framework.invocation.timeouts.Timeouts;
import io.perfeccionista.framework.repeater.RepeatPolicy;
import io.perfeccionista.framework.service.UseService;
import org.jetbrains.annotations.NotNull;

import static org.mockito.Mockito.mock;

@UseService(service = FixtureService.class, configuration = TestFixtureServiceConfiguration.class)
public class FixtureLocalEnvironmentConfiguration implements EnvironmentConfiguration {

    private final InvocationRunnerConfiguration actionRunnerConfiguration;
    private final RepeatPolicy repeatPolicy;
    private final Timeouts timeouts;

    public FixtureLocalEnvironmentConfiguration() {
        this.actionRunnerConfiguration = mock(InvocationRunnerConfiguration.class);
        this.repeatPolicy = mock(RepeatPolicy.class);
        this.timeouts = new DefaultTimeouts();
    }

    @Override
    public @NotNull InvocationRunnerConfiguration getActionRunnerConfiguration() {
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
