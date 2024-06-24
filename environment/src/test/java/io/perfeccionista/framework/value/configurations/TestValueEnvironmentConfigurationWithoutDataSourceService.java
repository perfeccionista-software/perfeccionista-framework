package io.perfeccionista.framework.value.configurations;

import io.perfeccionista.framework.DefaultEnvironmentConfiguration;
import io.perfeccionista.framework.datasource.DataSourceService;
import io.perfeccionista.framework.service.ServiceConfigurationManager;
import org.jetbrains.annotations.NotNull;

public class TestValueEnvironmentConfigurationWithoutDataSourceService extends DefaultEnvironmentConfiguration {

    @Override
    public @NotNull ServiceConfigurationManager getServiceConfigurations() {
        return super.getServiceConfigurations()
                .removeByServiceClass(DataSourceService.class);
    }

}
