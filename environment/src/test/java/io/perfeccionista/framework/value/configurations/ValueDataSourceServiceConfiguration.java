package io.perfeccionista.framework.value.configurations;

import io.perfeccionista.framework.datasource.DataSourceHolder;
import io.perfeccionista.framework.datasource.DefaultDataSourceServiceConfiguration;
import io.perfeccionista.framework.datasource.Stash;
import io.perfeccionista.framework.value.implementations.StringDataSource;
import io.perfeccionista.framework.value.implementations.UserDataSource;

import java.util.stream.Stream;

public class ValueDataSourceServiceConfiguration extends DefaultDataSourceServiceConfiguration {

    @Override
    public Stream<DataSourceHolder<?>> dataSourceHolders() {
        return Stream.of(
                DataSourceHolder.of(Stash.class),
                DataSourceHolder.of(StringDataSource.class),
                DataSourceHolder.of(UserDataSource.class)
        );
    }

}
