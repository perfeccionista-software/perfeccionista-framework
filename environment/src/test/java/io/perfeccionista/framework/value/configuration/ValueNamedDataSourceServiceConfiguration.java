package io.perfeccionista.framework.value.configuration;

import io.perfeccionista.framework.datasource.DataSource;
import io.perfeccionista.framework.datasource.DataSourceServiceConfiguration;
import io.perfeccionista.framework.value.implementations.StringDataSource;
import io.perfeccionista.framework.value.implementations.UserDataSource;

import java.util.Optional;
import java.util.stream.Stream;

public class ValueNamedDataSourceServiceConfiguration implements DataSourceServiceConfiguration {

    private final DataSource<?, ?> defaultDataSource;

    public ValueNamedDataSourceServiceConfiguration() {
        this.defaultDataSource = new StringDataSource();
    }

    @Override
    public Stream<DataSource<?, ?>> getDataSources() {
        return Stream.of(
                this.defaultDataSource,
                new UserDataSource()
        );
    }

    @Override
    public Optional<DataSource<?, ?>> getDefaultDataSource() {
        return Optional.of(this.defaultDataSource);
    }

}



