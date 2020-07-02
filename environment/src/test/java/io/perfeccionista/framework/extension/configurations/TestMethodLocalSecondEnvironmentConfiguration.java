package io.perfeccionista.framework.extension.configurations;

import org.jetbrains.annotations.NotNull;
import io.perfeccionista.framework.EnvironmentConfiguration;
import io.perfeccionista.framework.invocation.runner.InvocationRunnerConfiguration;
import io.perfeccionista.framework.invocation.timeouts.Timeouts;
import io.perfeccionista.framework.extension.services.TestService1;
import io.perfeccionista.framework.extension.services.TestService2;
import io.perfeccionista.framework.extension.services.configurations.TestServiceConfiguration1;
import io.perfeccionista.framework.extension.services.configurations.TestServiceConfiguration3;
import io.perfeccionista.framework.repeater.RepeatPolicy;
import io.perfeccionista.framework.service.UseService;

import static org.mockito.Mockito.mock;

@UseService(service = TestService1.class, configuration = TestServiceConfiguration1.class)
@UseService(service = TestService2.class, configuration = TestServiceConfiguration3.class, disabled = true, order = 89)
public class TestMethodLocalSecondEnvironmentConfiguration implements EnvironmentConfiguration {

    @Override
    public @NotNull InvocationRunnerConfiguration getActionRunnerConfiguration() {
        return mock(InvocationRunnerConfiguration.class);
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
