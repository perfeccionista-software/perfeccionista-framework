package io.perfeccionista.framework.value.configurations;

import io.perfeccionista.framework.DefaultEnvironmentConfiguration;
import io.perfeccionista.framework.datasource.DataSourceService;
import io.perfeccionista.framework.service.ConfiguredServiceHolder;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class TestValueEnvironmentConfigurationWithoutDataSourceService extends DefaultEnvironmentConfiguration {

    @Override
    public @NotNull Set<ConfiguredServiceHolder> getServiceConfigurations() {
        return super.getServiceConfigurations().stream()
                .filter(configuredServiceHolder -> !Objects.equals(configuredServiceHolder.getServiceClass(), DataSourceService.class))
                .collect(toSet());
    }

}
