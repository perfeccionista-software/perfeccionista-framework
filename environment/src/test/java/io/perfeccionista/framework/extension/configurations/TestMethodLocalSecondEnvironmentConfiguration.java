package io.perfeccionista.framework.extension.configurations;

import io.perfeccionista.framework.DefaultEnvironmentConfiguration;
import io.perfeccionista.framework.extension.services.TestService1;
import io.perfeccionista.framework.extension.services.TestService2;
import io.perfeccionista.framework.extension.services.configurations.TestServiceConfiguration1;
import io.perfeccionista.framework.extension.services.configurations.TestServiceConfiguration3;
import io.perfeccionista.framework.service.ConfiguredServiceHolder;
import io.perfeccionista.framework.service.Service;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestMethodLocalSecondEnvironmentConfiguration extends DefaultEnvironmentConfiguration {

    @Override
    public @NotNull Map<Class<? extends Service>, ConfiguredServiceHolder> getServices() {
        ConfiguredServiceHolder testService1 = ConfiguredServiceHolder.of(TestService1.class, TestServiceConfiguration1.class);
        ConfiguredServiceHolder testService2 = ConfiguredServiceHolder.of(TestService2.class, TestServiceConfiguration3.class)
                .setOrder(89)
                .disable();

        return Stream.of(
                Map.entry(TestService1.class, testService1),
                Map.entry(TestService2.class, testService2)
        ).collect(Collectors.toMap(Entry::getKey, Entry::getValue));
    }

}
