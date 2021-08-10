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
import io.perfeccionista.framework.service.Service;
import io.perfeccionista.framework.value.ValueService;
import org.jetbrains.annotations.NotNull;

import java.util.AbstractMap.SimpleEntry;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestClassLocalEnvironmentConfiguration extends DefaultEnvironmentConfiguration {

    @Override
    public @NotNull Map<Class<? extends Service>, ConfiguredServiceHolder> getServices() {
        ConfiguredServiceHolder valueService = ConfiguredServiceHolder.disabled(ValueService.class);
        ConfiguredServiceHolder testService1 = ConfiguredServiceHolder.of(TestService1.class, TestServiceConfiguration1.class)
                .setOrder(-100);
        ConfiguredServiceHolder testService2 = ConfiguredServiceHolder.of(TestService2.class, TestServiceConfiguration2.class);
        ConfiguredServiceHolder testService3 = ConfiguredServiceHolder.of(TestService3.class, TestServiceConfiguration3.class)
                .setOrder(2);
        ConfiguredServiceHolder testService4 = ConfiguredServiceHolder.of(TestService4.class, TestServiceConfiguration2.class)
                .setOrder(400)
                .disable();

        return Stream.of(
                new SimpleEntry<>(ValueService.class, valueService),
                new SimpleEntry<>(TestService1.class, testService1),
                new SimpleEntry<>(TestService2.class, testService2),
                new SimpleEntry<>(TestService3.class, testService3),
                new SimpleEntry<>(TestService4.class, testService4)
        ).collect(Collectors.toMap(Entry::getKey, Entry::getValue));
    }

}
