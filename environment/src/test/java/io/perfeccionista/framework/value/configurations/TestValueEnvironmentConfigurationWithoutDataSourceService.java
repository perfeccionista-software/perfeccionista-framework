package io.perfeccionista.framework.value.configurations;

import io.perfeccionista.framework.DefaultEnvironmentConfiguration;
import io.perfeccionista.framework.datasource.DataSourceService;
import io.perfeccionista.framework.service.ConfiguredServiceHolder;
import io.perfeccionista.framework.service.Service;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class TestValueEnvironmentConfigurationWithoutDataSourceService extends DefaultEnvironmentConfiguration {

    @Override
    public @NotNull Map<Class<? extends Service>, ConfiguredServiceHolder> getServices() {
        Map<Class<? extends Service>, ConfiguredServiceHolder> services = super.getServices();
        services.remove(DataSourceService.class);
        return services;
    }

}
