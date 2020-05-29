package io.perfeccionista.framework.pagefactory.configurations.datasource;

import io.perfeccionista.framework.datasource.DataSource;
import io.perfeccionista.framework.datasource.DataSourceServiceConfiguration;

import java.util.Optional;
import java.util.stream.Stream;

public class TestDataSourceServiceConfiguration implements DataSourceServiceConfiguration {

    private final TestStashDataStorage stash;

    public TestDataSourceServiceConfiguration() {
        this.stash = new TestStashDataStorage();
    }

    @Override
    public Stream<DataSource<?, ?>> getDataSources() {
        return Stream.of(
                new TestPropertiesDataSource(),
                stash
        );
    }

    @Override
    public Optional<DataSource<?, ?>> getDefaultDataSource() {
        return Optional.of(stash);
    }

}
