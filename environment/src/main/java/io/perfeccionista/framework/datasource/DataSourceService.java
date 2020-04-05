package io.perfeccionista.framework.datasource;

import io.perfeccionista.framework.Environment;
import org.jetbrains.annotations.NotNull;
import io.perfeccionista.framework.exceptions.DataSourceNotFoundException;
import io.perfeccionista.framework.exceptions.IncorrectServiceConfigurationException;
import io.perfeccionista.framework.exceptions.RegisterDuplicateException;
import io.perfeccionista.framework.service.Service;
import io.perfeccionista.framework.service.ServiceConfiguration;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.CHECK_CONFIGURATION_NOT_VALID;
import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.DATA_SOURCE_NOT_FOUND_BY_CLASS;
import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.DATA_SOURCE_REGISTER_BY_CLASS_DUPLICATE;
import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.DEFAULT_DATA_SOURCE_NOT_DECLARED;

public class DataSourceService implements Service {

    protected DataSourceServiceConfiguration validatedConfiguration;
    protected Map<Class<? extends DataSource>, DataSource<?, ?>> dataSourcesByClass = new HashMap<>();

    @Override
    public void init(@NotNull Environment environment, @NotNull ServiceConfiguration serviceConfiguration) {
        validatedConfiguration = validate(serviceConfiguration);
        validatedConfiguration.getDataSources().forEach(dataSource -> {
            Class<? extends DataSource> dataSourceClass = dataSource.getClass();
            if (dataSourcesByClass.containsKey(dataSourceClass)) {
                throw new RegisterDuplicateException(DATA_SOURCE_REGISTER_BY_CLASS_DUPLICATE.getMessage(dataSourceClass.getCanonicalName()));
            }
            dataSourcesByClass.put(dataSourceClass, dataSource);
        });
    }

    public boolean contains(Class<? extends DataSource> dataSourceClass) {
        return dataSourcesByClass.containsKey(dataSourceClass);
    }

    public <T extends DataSource> T get(Class<T> dataSourceClass) {
        DataSource<?, ?> dataSource = dataSourcesByClass.get(dataSourceClass);
        if (null == dataSource) {
            throw new DataSourceNotFoundException(DATA_SOURCE_NOT_FOUND_BY_CLASS.getMessage(dataSourceClass.getCanonicalName()));
        }
        return (T) dataSourcesByClass.get(dataSourceClass);
    }

    public <T extends DataSource> T getDefault() {
        if (validatedConfiguration.getDefaultDataSource().isPresent()) {
            return (T) validatedConfiguration.getDefaultDataSource().get();
        }
        throw new DataSourceNotFoundException(DEFAULT_DATA_SOURCE_NOT_DECLARED.getMessage());
    }

    public Stream<DataSource<?, ?>> stream() {
        return dataSourcesByClass.values().stream();
    }

    protected DataSourceServiceConfiguration validate(ServiceConfiguration configuration) {
        if (configuration instanceof DataSourceServiceConfiguration) {
            return (DataSourceServiceConfiguration) configuration;
        }
        throw new IncorrectServiceConfigurationException(
                CHECK_CONFIGURATION_NOT_VALID.getMessage(configuration.getClass().getCanonicalName(), this.getClass().getCanonicalName()));
    }

}
