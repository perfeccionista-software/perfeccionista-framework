package io.perfeccionista.framework.datasource.configuration;

import org.jetbrains.annotations.NotNull;
import io.perfeccionista.framework.EnvironmentConfiguration;
import io.perfeccionista.framework.action.runner.ActionRunnerConfiguration;
import io.perfeccionista.framework.action.timeouts.DefaultTimeouts;
import io.perfeccionista.framework.action.timeouts.Timeouts;
import io.perfeccionista.framework.datasource.DataSourceService;
import io.perfeccionista.framework.repeater.RepeatPolicy;
import io.perfeccionista.framework.service.UseService;

import static org.mockito.Mockito.mock;

@UseService(service = DataSourceService.class, configuration = TestDataSourceServiceConfiguration.class)
public class DataSourceLocalEnvironmentConfiguration implements EnvironmentConfiguration {

    private final ActionRunnerConfiguration actionRunnerConfiguration;
    private final RepeatPolicy repeatPolicy;
    private final Timeouts timeouts;

    public DataSourceLocalEnvironmentConfiguration() {
        this.actionRunnerConfiguration = mock(ActionRunnerConfiguration.class);
        this.repeatPolicy = mock(RepeatPolicy.class);
        this.timeouts = new DefaultTimeouts();
    }

    @Override
    public @NotNull ActionRunnerConfiguration getActionRunnerConfiguration() {
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