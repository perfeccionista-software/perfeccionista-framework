package io.perfeccionista.framework.datasource;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.service.DefaultServiceConfiguration;
import org.jetbrains.annotations.NotNull;
import io.perfeccionista.framework.exceptions.DataConverterNotFound;
import io.perfeccionista.framework.exceptions.IncorrectServiceConfiguration;
import io.perfeccionista.framework.service.Service;
import io.perfeccionista.framework.service.ServiceConfiguration;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.CHECK_CONFIGURATION_NOT_VALID;
import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.DATA_CONVERTER_NOT_FOUND_BY_CLASS;
import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.DATA_CONVERTER_NOT_FOUND_BY_NAME;

@DefaultServiceConfiguration(DefaultDataConverterServiceConfiguration.class)
public class DataConverterService implements Service {

    protected DataConverterServiceConfiguration validatedConfiguration;
    protected Map<String, DataConverter<?, ?>> dataConvertersByName = new HashMap<>();

    @Override
    public void init(@NotNull Environment environment, @NotNull ServiceConfiguration configuration) {
        validatedConfiguration = validate(configuration);
        dataConvertersByName = validatedConfiguration.getDataConverters();
    }

    public Stream<DataConverter<?, ?>> stream() {
        return dataConvertersByName.values().stream().distinct();
    }

    public Stream<String> names() {
        return dataConvertersByName.keySet().stream();
    }

    public boolean contains(Class<? extends DataConverter> dataConverterClass) {
        return dataConvertersByName.values().stream()
                .anyMatch(dataConverter -> dataConverterClass.isAssignableFrom(dataConverter.getClass()));
    }

    public boolean contains(String dataConverterName) {
        return dataConvertersByName.containsKey(dataConverterName);
    }

    @SuppressWarnings("unchecked")
    public <T extends DataConverter> T get(Class<T> dataConverterClass) {
        return (T) dataConvertersByName.values().stream()
                .filter(dataConverter -> dataConverterClass.equals(dataConverter.getClass()))
                .findFirst()
                .orElseThrow(() -> DataConverterNotFound.exception(DATA_CONVERTER_NOT_FOUND_BY_CLASS.getMessage(dataConverterClass.getCanonicalName())));
    }

    public <T extends DataConverter> T get(String dataConverterName) {
        DataConverter<?, ?> dataConverter = dataConvertersByName.get(dataConverterName);
        if (null == dataConverter) {
            throw DataConverterNotFound.exception(DATA_CONVERTER_NOT_FOUND_BY_NAME.getMessage(dataConverterName));
        }
        return (T) dataConvertersByName.get(dataConverterName);
    }

    protected DataConverterServiceConfiguration validate(ServiceConfiguration configuration) {
        if (configuration instanceof DataConverterServiceConfiguration) {
            return (DataConverterServiceConfiguration) configuration;
        }
        throw IncorrectServiceConfiguration.exception(
                CHECK_CONFIGURATION_NOT_VALID.getMessage(configuration.getClass().getCanonicalName(), this.getClass().getCanonicalName()));
    }

}
