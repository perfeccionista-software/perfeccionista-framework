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
import io.perfeccionista.framework.service.ServiceConfigurationManager;
import io.perfeccionista.framework.value.ValueService;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class TestEnvironmentParameterResolverConfiguration extends DefaultEnvironmentConfiguration {

    @Override
    public @NotNull ServiceConfigurationManager getServiceConfigurations() {
        return ServiceConfigurationManager.of()
                .put(ConfiguredServiceHolder.of(ValueService.class))
                .put(ConfiguredServiceHolder.of(TestService1.class, new TestServiceConfiguration1())
                        .setOrder(-100))
                .put(ConfiguredServiceHolder.of(TestService2.class, new TestServiceConfiguration2()))
                .put(ConfiguredServiceHolder.of(TestService3.class, new TestServiceConfiguration3())
                        .setOrder(2))
                .put(ConfiguredServiceHolder.of(TestService4.class, new TestServiceConfiguration2())
                        .setOrder(400));
    }

}
