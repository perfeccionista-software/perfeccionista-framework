package io.perfeccionista.framework.extension.configurations;

import io.perfeccionista.framework.DefaultEnvironmentConfiguration;
import io.perfeccionista.framework.extension.services.TestService1;
import io.perfeccionista.framework.extension.services.TestService2;
import io.perfeccionista.framework.extension.services.configurations.TestServiceConfiguration1;
import io.perfeccionista.framework.extension.services.configurations.TestServiceConfiguration3;
import io.perfeccionista.framework.service.ConfiguredServiceHolder;
import io.perfeccionista.framework.service.ServiceConfigurationManager;
import org.jetbrains.annotations.NotNull;

public class TestMethodLocalSecondEnvironmentConfiguration extends DefaultEnvironmentConfiguration {

    @Override
    public @NotNull ServiceConfigurationManager getServiceConfigurations() {
        return ServiceConfigurationManager.of()
                .put(ConfiguredServiceHolder.of(TestService1.class, new TestServiceConfiguration1()))
                .put(ConfiguredServiceHolder.of(TestService2.class, new TestServiceConfiguration3())
                        .setOrder(89)
                        .disable());
    }

}
