package io.perfeccionista.framework.extension.configurations;

import io.perfeccionista.framework.DefaultEnvironmentConfiguration;
import io.perfeccionista.framework.extension.services.TestService1;
import io.perfeccionista.framework.extension.services.TestService2;
import io.perfeccionista.framework.extension.services.configurations.TestServiceConfiguration1;
import io.perfeccionista.framework.extension.services.configurations.TestServiceConfiguration3;
import io.perfeccionista.framework.service.ConfiguredServiceHolder;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class TestMethodLocalSecondEnvironmentConfiguration extends DefaultEnvironmentConfiguration {

    @Override
    public @NotNull Set<ConfiguredServiceHolder> getServiceConfigurations() {
        return Set.of(
                ConfiguredServiceHolder.of(TestService1.class, TestServiceConfiguration1.class),
                ConfiguredServiceHolder.of(TestService2.class, TestServiceConfiguration3.class)
                        .setOrder(89)
                        .disable()
        );
    }

}
