package io.perfeccionista.framework.datasource;

import io.perfeccionista.framework.service.ServiceConfiguration;

import java.util.Map;
import java.util.Optional;

public interface DataSourceServiceConfiguration extends ServiceConfiguration {

    Map<String, DataSource<?, ?>> getDataSources();

    Optional<DataSource<?, ?>> getDefaultDataSource(Map<String, DataSource<?, ?>> dataSources);

}
