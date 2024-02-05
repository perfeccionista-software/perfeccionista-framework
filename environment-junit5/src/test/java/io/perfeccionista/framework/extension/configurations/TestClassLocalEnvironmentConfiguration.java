package io.perfeccionista.framework.extension.configurations;

import io.perfeccionista.framework.DefaultEnvironmentConfiguration;
import io.perfeccionista.framework.extension.services.TestService1;
import io.perfeccionista.framework.extension.services.TestService2;
import io.perfeccionista.framework.extension.services.TestService3;
import io.perfeccionista.framework.extension.services.TestService4;
import io.perfeccionista.framework.extension.services.configurations.TestServiceConfiguration1;
import io.perfeccionista.framework.extension.services.configurations.TestServiceConfiguration2;
import io.perfeccionista.framework.extension.services.configurations.TestServiceConfiguration3;
import io.perfeccionista.framework.service.ConfiguredServiceHolder;
import io.perfeccionista.framework.value.ValueService;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class TestClassLocalEnvironmentConfiguration extends DefaultEnvironmentConfiguration {

    @Override
    public @NotNull Set<ConfiguredServiceHolder> getServiceConfigurations() {
        return Set.of(
                ConfiguredServiceHolder.of(ValueService.class)
                        .disable(),
                ConfiguredServiceHolder.of(TestService1.class, TestServiceConfiguration1.class)
                        .setOrder(-100),
                ConfiguredServiceHolder.of(TestService2.class, TestServiceConfiguration2.class),
                ConfiguredServiceHolder.of(TestService3.class, TestServiceConfiguration3.class)
                        .setOrder(2),
                ConfiguredServiceHolder.of(TestService4.class, TestServiceConfiguration2.class)
                        .setOrder(400)
                        .disable()
        );
    }

}
