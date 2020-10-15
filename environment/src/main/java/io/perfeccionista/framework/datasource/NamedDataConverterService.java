package io.perfeccionista.framework.datasource;

import io.perfeccionista.framework.Environment;
import org.jetbrains.annotations.NotNull;
import io.perfeccionista.framework.exceptions.DataConverterNotFound;
import io.perfeccionista.framework.exceptions.RegisterDuplicate;
import io.perfeccionista.framework.name.Name;
import io.perfeccionista.framework.service.ServiceConfiguration;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.platform.commons.util.AnnotationUtils.findRepeatableAnnotations;
import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.DATA_CONVERTER_NOT_FOUND_BY_NAME;
import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.DATA_CONVERTER_REGISTER_BY_CLASS_DUPLICATE;
import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.DATA_CONVERTER_REGISTER_BY_NAME_DUPLICATE;

public class NamedDataConverterService extends DataConverterService {

    protected Map<String, DataConverter<?, ?>> dataConvertersByName = new HashMap<>();

    @Override
    public void init(@NotNull Environment environment, @NotNull ServiceConfiguration serviceConfiguration) {
        validatedConfiguration = validate(serviceConfiguration);
        validatedConfiguration.getDataConverters().forEach(dataConverter -> {
            Class<? extends DataConverter> dataConverterClass = dataConverter.getClass();
            if (dataConvertersByClass.containsKey(dataConverterClass)) {
                throw RegisterDuplicate.exception(DATA_CONVERTER_REGISTER_BY_CLASS_DUPLICATE.getMessage(dataConverterClass.getCanonicalName()));
            }
            findRepeatableAnnotations(dataConverterClass, Name.class).stream()
                    .map(Name::value)
                    .forEach(name -> {
                        if (dataConvertersByName.containsKey(name)) {
                            throw RegisterDuplicate.exception(DATA_CONVERTER_REGISTER_BY_NAME_DUPLICATE.getMessage(name));
                        }
                        dataConvertersByName.put(name, dataConverter);
                    });
            dataConvertersByClass.put(dataConverterClass, dataConverter);
        });
    }

    public boolean contains(String dataConverterName) {
        return dataConvertersByName.containsKey(dataConverterName);
    }

    public <T extends DataConverter> T get(String dataConverterName) {
        DataConverter<?, ?> dataConverter = dataConvertersByName.get(dataConverterName);
        if (null == dataConverter) {
            throw DataConverterNotFound.exception(DATA_CONVERTER_NOT_FOUND_BY_NAME.getMessage(dataConverterName));
        }
        return (T) dataConvertersByName.get(dataConverterName);
    }

    public Stream<String> names() {
        return dataConvertersByName.keySet().stream();
    }

}