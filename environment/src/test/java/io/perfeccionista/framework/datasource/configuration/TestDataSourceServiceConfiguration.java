package io.perfeccionista.framework.datasource.configuration;

import io.perfeccionista.framework.datasource.DataSource;
import io.perfeccionista.framework.datasource.DataSourceServiceConfiguration;
import io.perfeccionista.framework.datasource.implementations.SimpleDataSource;
import io.perfeccionista.framework.datasource.implementations.SimpleDataStorage;
import io.perfeccionista.framework.datasource.implementations.entities.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public class TestDataSourceServiceConfiguration implements DataSourceServiceConfiguration {

    private final DataSource<?, ?> defaultDataSource;

    public TestDataSourceServiceConfiguration() {
        this.defaultDataSource = new SimpleDataSource();
    }

    @Override
    public Stream<DataSource<?, ?>> getDataSources() {
        return Stream.of(
                this.defaultDataSource,
                new SimpleDataStorage(createInitialData()));
    }

    @Override
    public Optional<DataSource<?, ?>> getDefaultDataSource() {
        return Optional.of(this.defaultDataSource);
    }

    private Map<String, User> createInitialData() {
        Map<String, User> users = new HashMap<>();
        users.put("Jack", new User("Jack", "Black"));
        return users;
    }

}
