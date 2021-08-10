package io.perfeccionista.framework.extension.configurations;

import io.perfeccionista.framework.DefaultEnvironmentConfiguration;
import io.perfeccionista.framework.extension.services.TestService2;
import io.perfeccionista.framework.extension.services.TestService3;
import io.perfeccionista.framework.extension.services.TestService4;
import io.perfeccionista.framework.extension.services.configurations.TestServiceConfiguration1;
import io.perfeccionista.framework.extension.services.configurations.TestServiceConfiguration2;
import io.perfeccionista.framework.extension.services.configurations.TestServiceConfiguration3;
import io.perfeccionista.framework.service.ConfiguredServiceHolder;
import io.perfeccionista.framework.service.Service;
import org.jetbrains.annotations.NotNull;

import java.util.AbstractMap.SimpleEntry;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestMethodLocalFirstEnvironmentConfiguration extends DefaultEnvironmentConfiguration {

    @Override
    public @NotNull Map<Class<? extends Service>, ConfiguredServiceHolder> getServices() {
        ConfiguredServiceHolder testService2 = ConfiguredServiceHolder.of(TestService2.class, TestServiceConfiguration1.class);
        ConfiguredServiceHolder testService3 = ConfiguredServiceHolder.of(TestService3.class, TestServiceConfiguration3.class)
                .setOrder(-2)
                .disable();
        ConfiguredServiceHolder testService4 = ConfiguredServiceHolder.of(TestService4.class, TestServiceConfiguration2.class)
                .setOrder(4);

        return Stream.of(
                new SimpleEntry<>(TestService2.class, testService2),
                new SimpleEntry<>(TestService3.class, testService3),
                new SimpleEntry<>(TestService4.class, testService4)
        ).collect(Collectors.toMap(Entry::getKey, Entry::getValue));
    }

}
