package io.perfeccionista.framework.datasource;

import org.jetbrains.annotations.NotNull;
import io.perfeccionista.framework.exceptions.DataConverterNotFoundException;
import io.perfeccionista.framework.exceptions.IncorrectServiceConfigurationException;
import io.perfeccionista.framework.exceptions.RegisterDuplicateException;
import io.perfeccionista.framework.service.Service;
import io.perfeccionista.framework.service.ServiceConfiguration;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.CHECK_CONFIGURATION_NOT_VALID;
import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.DATA_CONVERTER_NOT_FOUND_BY_CLASS;
import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.DATA_CONVERTER_REGISTER_BY_CLASS_DUPLICATE;

public class DataConverterService implements Service {

    protected DataConverterServiceConfiguration validatedConfiguration;
    protected Map<Class<? extends DataConverter>, DataConverter<?, ?>> dataConvertersByClass = new HashMap<>();

    @Override
    public void init(@NotNull ServiceConfiguration serviceConfiguration) {
        DataConverterServiceConfiguration validatedConfiguration = validate(serviceConfiguration);
        validatedConfiguration.getDataConverters().forEach(dataConverter -> {
            Class<? extends DataConverter> dataConverterClass = dataConverter.getClass();
            if (dataConvertersByClass.containsKey(dataConverterClass)) {
                throw new RegisterDuplicateException(DATA_CONVERTER_REGISTER_BY_CLASS_DUPLICATE.getMessage(dataConverterClass.getCanonicalName()));
            }
            dataConvertersByClass.put(dataConverterClass, dataConverter);
        });
    }

    public boolean contains(Class<? extends DataConverter> dataConverterClass) {
        return dataConvertersByClass.containsKey(dataConverterClass);
    }

    public <T extends DataConverter> T get(Class<T> dataConverterClass) {
        DataConverter<?, ?> dataConverter = dataConvertersByClass.get(dataConverterClass);
        if (null == dataConverter) {
            throw new DataConverterNotFoundException(DATA_CONVERTER_NOT_FOUND_BY_CLASS.getMessage(dataConverterClass.getCanonicalName()));
        }
        return (T) dataConvertersByClass.get(dataConverterClass);
    }

    public Stream<DataConverter<?, ?>> stream() {
        return dataConvertersByClass.values().stream();
    }

    protected DataConverterServiceConfiguration validate(ServiceConfiguration configuration) {
        if (configuration instanceof DataConverterServiceConfiguration) {
            return (DataConverterServiceConfiguration) configuration;
        }
        throw new IncorrectServiceConfigurationException(
                CHECK_CONFIGURATION_NOT_VALID.getMessage(configuration.getClass().getCanonicalName(), this.getClass().getCanonicalName()));
    }

}
