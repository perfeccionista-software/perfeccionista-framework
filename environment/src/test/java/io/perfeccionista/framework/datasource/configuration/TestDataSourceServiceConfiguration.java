package io.perfeccionista.framework.datasource.configuration;

import io.perfeccionista.framework.datasource.DataSourceHolder;
import io.perfeccionista.framework.datasource.DefaultDataSourceServiceConfiguration;
import io.perfeccionista.framework.datasource.Stash;
import io.perfeccionista.framework.datasource.implementations.SimpleDataSource;
import io.perfeccionista.framework.datasource.implementations.SimpleDataStorage;
import io.perfeccionista.framework.value.implementations.StringDataSource;
import io.perfeccionista.framework.value.implementations.UserDataSource;

import java.util.stream.Stream;

public class TestDataSourceServiceConfiguration extends DefaultDataSourceServiceConfiguration {

    @Override
    public Stream<DataSourceHolder<?>> dataSourceHolders() {
        return Stream.of(
                DataSourceHolder.of(Stash.class),
                DataSourceHolder.of(SimpleDataSource.class),
                DataSourceHolder.of(SimpleDataStorage.class),
                DataSourceHolder.of(StringDataSource.class),
                DataSourceHolder.of(UserDataSource.class)
        );
    }

}
