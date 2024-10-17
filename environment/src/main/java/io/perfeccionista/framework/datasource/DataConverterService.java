package io.perfeccionista.framework.datasource;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.exceptions.RegisterDuplicate;
import io.perfeccionista.framework.exceptions.attachments.TextAttachmentEntry;
import io.perfeccionista.framework.preconditions.Preconditions;
import io.perfeccionista.framework.service.DefaultServiceConfiguration;
import org.jetbrains.annotations.NotNull;
import io.perfeccionista.framework.exceptions.DataConverterNotFound;
import io.perfeccionista.framework.service.Service;
import io.perfeccionista.framework.service.ServiceConfiguration;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.DATA_CONVERTER_REGISTER_BY_CLASS_DUPLICATE;
import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.DATA_CONVERTER_REGISTER_BY_NAME_DUPLICATE;
import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.DATA_CONVERTER_NOT_FOUND_BY_CLASS;
import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.DATA_CONVERTER_NOT_FOUND_BY_NAME;

@DefaultServiceConfiguration(DefaultDataConverterServiceConfiguration.class)
public class DataConverterService implements Service {

    protected Environment environment;
    protected DataConverterServiceConfiguration validatedConfiguration;

    protected Map<String, DataConverter<?, ?>> dataConvertersByName = new HashMap<>();
    protected Map<Class<? extends DataConverter<?, ?>>, DataConverter<?, ?>> dataConvertersByClass = new HashMap<>();

    @Override
    public void init(@NotNull Environment environment) {
        Preconditions.notNull(environment, "Environment must not be null");
        this.environment = environment;
    }

    @Override
    public void init(@NotNull Environment environment, @NotNull ServiceConfiguration configuration) {
        Preconditions.notNull(environment, "Environment must not be null");
        Preconditions.notNull(configuration, "Service configuration must not be null");
        this.environment = environment;
        this.validatedConfiguration = validate(configuration, DataConverterServiceConfiguration.class);
        this.validatedConfiguration.dataConverterHolders().forEach(dataConverterHolder -> {
            Class<? extends DataConverter<?, ?>> dataConverterClass = dataConverterHolder.getDataConverterClass();
            if (dataConvertersByClass.containsKey(dataConverterClass)) {
                throw RegisterDuplicate.exception(DATA_CONVERTER_REGISTER_BY_CLASS_DUPLICATE.getMessage(dataConverterClass.getCanonicalName()));
            }
            DataConverter<?, ?> dataConverterInstance = dataConverterHolder.getDataConverterInstance();
            dataConvertersByClass.put(dataConverterClass, dataConverterInstance);
            dataConverterHolder.getNames().forEach(name -> {
                if (dataConvertersByName.containsKey(name)) {
                    throw RegisterDuplicate.exception(DATA_CONVERTER_REGISTER_BY_NAME_DUPLICATE.getMessage(name))
                            .addLastAttachmentEntry(TextAttachmentEntry.of("First DataConverter class with name " + name,
                                    dataConverterHolder.getDataConverterClass().getCanonicalName()))
                            .addLastAttachmentEntry(TextAttachmentEntry.of("Second DataConverter class with name " + name,
                                    dataConvertersByName.get(name).getClass().getCanonicalName()));
                }
                dataConvertersByName.put(name, dataConverterInstance);
            });
        });
    }

    public Stream<DataConverter<?, ?>> stream() {
        return dataConvertersByClass.values().stream().distinct();
    }

    public Stream<String> names() {
        return dataConvertersByName.keySet().stream();
    }

    public Stream<Class<? extends DataConverter<?, ?>>> classes() {
        return dataConvertersByClass.keySet().stream();
    }

    public boolean contains(Class<? extends DataConverter<?, ?>> dataConverterClass) {
        return dataConvertersByClass.containsKey(dataConverterClass);
    }

    public boolean contains(String dataConverterName) {
        return dataConvertersByName.containsKey(dataConverterName);
    }

    @SuppressWarnings("unchecked")
    public <T extends DataConverter<?, ?>> T get(Class<T> dataConverterClass) {
        DataConverter<?, ?> dataConverter = dataConvertersByClass.get(dataConverterClass);
        if (null == dataConverter) {
            throw DataConverterNotFound.exception(DATA_CONVERTER_NOT_FOUND_BY_CLASS.getMessage(dataConverterClass.getCanonicalName()));
        }
        return (T) dataConvertersByClass.get(dataConverterClass);
    }

    public <T extends DataConverter<?, ?>> T get(String dataConverterName) {
        DataConverter<?, ?> dataConverter = dataConvertersByName.get(dataConverterName);
        if (null == dataConverter) {
            throw DataConverterNotFound.exception(DATA_CONVERTER_NOT_FOUND_BY_NAME.getMessage(dataConverterName));
        }
        return (T) dataConvertersByName.get(dataConverterName);
    }

}
