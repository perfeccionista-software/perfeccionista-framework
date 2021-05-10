package io.perfeccionista.framework.datasource;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.service.DefaultServiceConfiguration;
import org.jetbrains.annotations.NotNull;
import io.perfeccionista.framework.exceptions.DataSourceNotFound;
import io.perfeccionista.framework.exceptions.IncorrectServiceConfiguration;
import io.perfeccionista.framework.service.Service;
import io.perfeccionista.framework.service.ServiceConfiguration;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.SERVICE_CONFIGURATION_NOT_VALID;
import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.DATA_SOURCE_NOT_FOUND_BY_CLASS;
import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.DATA_SOURCE_NOT_FOUND_BY_NAME;
import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.DEFAULT_DATA_SOURCE_NOT_DECLARED;

@DefaultServiceConfiguration(DefaultDataSourceServiceConfiguration.class)
public class DataSourceService implements Service {

    protected DataSourceServiceConfiguration validatedConfiguration;
    protected Map<String, DataSource<?, ?>> dataSourcesByName = new HashMap<>();

    @Override
    public void init(@NotNull Environment environment, @NotNull ServiceConfiguration configuration) {
        validatedConfiguration = validate(configuration);
        dataSourcesByName = validatedConfiguration.getDataSources();
    }

    public Stream<DataSource<?, ?>> stream() {
        return dataSourcesByName.values().stream().distinct();
    }

    public Stream<String> names() {
        return dataSourcesByName.keySet().stream();
    }

    public boolean contains(Class<? extends DataSource> dataSourceClass) {
        return dataSourcesByName.values().stream()
                .anyMatch(dataSource -> dataSourceClass.isAssignableFrom(dataSource.getClass()));
    }

    public boolean contains(String dataSourceName) {
        return dataSourcesByName.containsKey(dataSourceName);
    }

    @SuppressWarnings("unchecked")
    public <T extends DataSource> T get(Class<T> dataSourceClass) {
        return (T) dataSourcesByName.values().stream()
                .filter(dataSource -> dataSourceClass.equals(dataSource.getClass()))
                .findFirst()
                .orElseThrow(() -> DataSourceNotFound.exception(DATA_SOURCE_NOT_FOUND_BY_CLASS.getMessage(dataSourceClass.getCanonicalName())));
    }

    public <T extends DataSource> T get(String dataSourceName) {
        DataSource<?, ?> dataSource = dataSourcesByName.get(dataSourceName);
        if (null == dataSource) {
            throw DataSourceNotFound.exception(DATA_SOURCE_NOT_FOUND_BY_NAME.getMessage(dataSourceName));
        }
        return (T) dataSourcesByName.get(dataSourceName);
    }

    public <T extends DataSource> T getDefault() {
        Optional<DataSource<?, ?>> optionalDataSource = validatedConfiguration.getDefaultDataSource(dataSourcesByName);
        if (optionalDataSource.isPresent()) {
            return (T) optionalDataSource.get();
        }
        throw DataSourceNotFound.exception(DEFAULT_DATA_SOURCE_NOT_DECLARED.getMessage());
    }

    protected DataSourceServiceConfiguration validate(ServiceConfiguration configuration) {
        if (configuration instanceof DataSourceServiceConfiguration) {
            return (DataSourceServiceConfiguration) configuration;
        }
        throw IncorrectServiceConfiguration.exception(
                SERVICE_CONFIGURATION_NOT_VALID.getMessage(configuration.getClass().getCanonicalName(), this.getClass().getCanonicalName()));
    }

}
