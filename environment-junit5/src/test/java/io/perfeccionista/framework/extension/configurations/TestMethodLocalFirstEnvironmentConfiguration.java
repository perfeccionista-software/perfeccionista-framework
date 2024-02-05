package io.perfeccionista.framework.extension.configurations;

import io.perfeccionista.framework.DefaultEnvironmentConfiguration;
import io.perfeccionista.framework.extension.services.TestService2;
import io.perfeccionista.framework.extension.services.TestService3;
import io.perfeccionista.framework.extension.services.TestService4;
import io.perfeccionista.framework.extension.services.configurations.TestServiceConfiguration1;
import io.perfeccionista.framework.extension.services.configurations.TestServiceConfiguration2;
import io.perfeccionista.framework.extension.services.configurations.TestServiceConfiguration3;
import io.perfeccionista.framework.service.ConfiguredServiceHolder;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class TestMethodLocalFirstEnvironmentConfiguration extends DefaultEnvironmentConfiguration {

    @Override
    public @NotNull Set<ConfiguredServiceHolder> getServiceConfigurations() {
        return Set.of(
                ConfiguredServiceHolder.of(TestService2.class, TestServiceConfiguration1.class),
                ConfiguredServiceHolder.of(TestService3.class, TestServiceConfiguration3.class)
                        .setOrder(-2)
                        .disable(),
                ConfiguredServiceHolder.of(TestService4.class, TestServiceConfiguration2.class)
                        .setOrder(4)
        );
    }

}
