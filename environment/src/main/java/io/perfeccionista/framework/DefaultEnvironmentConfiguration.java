package io.perfeccionista.framework;

import io.perfeccionista.framework.action.runner.ActionRunnerConfiguration;
import io.perfeccionista.framework.action.runner.DefaultActionRunnerConfiguration;
import io.perfeccionista.framework.action.timeouts.DefaultTimeouts;
import io.perfeccionista.framework.action.timeouts.Timeouts;
import io.perfeccionista.framework.repeater.NoRepeatPolicy;
import io.perfeccionista.framework.repeater.RepeatPolicy;
import io.perfeccionista.framework.service.UseService;
import io.perfeccionista.framework.value.DefaultValueServiceConfiguration;
import io.perfeccionista.framework.value.ValueService;
import org.jetbrains.annotations.NotNull;

@UseService(service = ValueService.class, configuration = DefaultValueServiceConfiguration.class)
public class DefaultEnvironmentConfiguration implements EnvironmentConfiguration {

    @Override
    public @NotNull ActionRunnerConfiguration getActionRunnerConfiguration() {
        return new DefaultActionRunnerConfiguration();
    }

    @Override
    public @NotNull RepeatPolicy getRepeatPolicy() {
        return new NoRepeatPolicy();
    }

    @Override
    public @NotNull Timeouts getTimeouts() {
        return new DefaultTimeouts();
    }

}
