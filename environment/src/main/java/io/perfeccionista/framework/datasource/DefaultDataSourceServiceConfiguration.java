package io.perfeccionista.framework.datasource;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

// Возвращает все
public class DefaultDataSourceServiceConfiguration implements DataSourceServiceConfiguration {

    @Override
    public Optional<DataSource<?, ?>> getDefaultDataSource(Map<Class<? extends DataSource<?, ?>>, DataSource<?, ?>> dataSources) {
        return Optional.ofNullable(dataSources.get(Stash.class));
    }

    @Override
    public Stream<DataSourceHolder<?>> dataSourceHolders() {
        return Stream.of(
                DataSourceHolder.of(Stash.class)
        );
    }

}
