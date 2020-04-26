package io.perfeccionista.framework.extension.configurations;

import io.perfeccionista.framework.value.ValueService;
import org.jetbrains.annotations.NotNull;
import io.perfeccionista.framework.EnvironmentConfiguration;
import io.perfeccionista.framework.action.runner.ActionRunnerConfiguration;
import io.perfeccionista.framework.action.timeouts.Timeouts;
import io.perfeccionista.framework.extension.services.TestService1;
import io.perfeccionista.framework.extension.services.TestService2;
import io.perfeccionista.framework.extension.services.TestService3;
import io.perfeccionista.framework.extension.services.TestService4;
import io.perfeccionista.framework.extension.services.configurations.TestServiceConfiguration1;
import io.perfeccionista.framework.extension.services.configurations.TestServiceConfiguration2;
import io.perfeccionista.framework.extension.services.configurations.TestServiceConfiguration3;
import io.perfeccionista.framework.repeater.RepeatPolicy;
import io.perfeccionista.framework.service.UseService;

import static org.mockito.Mockito.mock;

@UseService(service = ValueService.class, disabled = true)
@UseService(service = TestService1.class, configuration = TestServiceConfiguration1.class, order = -100)
@UseService(service = TestService2.class, configuration = TestServiceConfiguration2.class)
@UseService(service = TestService3.class, configuration = TestServiceConfiguration3.class, order = 2)
@UseService(service = TestService4.class, configuration = TestServiceConfiguration2.class, disabled = true, order = 400)
public class TestClassLocalEnvironmentConfiguration implements EnvironmentConfiguration {

    @Override
    public @NotNull ActionRunnerConfiguration getActionRunnerConfiguration() {
        return mock(ActionRunnerConfiguration.class);
    }

    @Override
    public @NotNull RepeatPolicy getRepeatPolicy() {
        return mock(RepeatPolicy.class);
    }

    @Override
    public @NotNull Timeouts getTimeouts() {
        return mock(Timeouts.class);
    }

}
