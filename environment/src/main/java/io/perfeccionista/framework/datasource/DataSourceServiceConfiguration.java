package io.perfeccionista.framework.datasource;

import io.perfeccionista.framework.service.ServiceConfiguration;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public interface DataSourceServiceConfiguration extends ServiceConfiguration {

    Optional<DataSource<?, ?>> getDefaultDataSource(Map<Class<? extends DataSource<?, ?>>, DataSource<?, ?>> dataSources);

    Stream<DataSourceHolder<?>> dataSourceHolders();

}
