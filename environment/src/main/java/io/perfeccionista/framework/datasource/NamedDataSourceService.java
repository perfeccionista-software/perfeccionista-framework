package io.perfeccionista.framework.datasource;

import io.perfeccionista.framework.Environment;
import org.jetbrains.annotations.NotNull;
import io.perfeccionista.framework.exceptions.DataSourceNotFound;
import io.perfeccionista.framework.exceptions.RegisterDuplicate;
import io.perfeccionista.framework.name.Name;
import io.perfeccionista.framework.service.ServiceConfiguration;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.platform.commons.util.AnnotationUtils.findRepeatableAnnotations;
import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.DATA_SOURCE_NOT_FOUND_BY_NAME;
import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.DATA_SOURCE_REGISTER_BY_CLASS_DUPLICATE;
import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.DATA_SOURCE_REGISTER_BY_NAME_DUPLICATE;

public class NamedDataSourceService extends DataSourceService {

    protected Map<String, DataSource<?, ?>> dataSourcesByName = new HashMap<>();

    @Override
    public void init(@NotNull Environment environment, @NotNull ServiceConfiguration serviceConfiguration) {
        validatedConfiguration = validate(serviceConfiguration);
        validatedConfiguration.getDataSources().forEach(dataSource -> {
            Class<? extends DataSource> dataSourceClass = dataSource.getClass();
            if (dataSourcesByClass.containsKey(dataSourceClass)) {
                throw RegisterDuplicate.exception(DATA_SOURCE_REGISTER_BY_CLASS_DUPLICATE.getMessage(dataSourceClass.getCanonicalName()));
            }
            findRepeatableAnnotations(dataSourceClass, Name.class).stream()
                    .map(Name::value)
                    .forEach(name -> {
                        if (dataSourcesByName.containsKey(name)) {
                            throw RegisterDuplicate.exception(DATA_SOURCE_REGISTER_BY_NAME_DUPLICATE.getMessage(name));
                        }
                        dataSourcesByName.put(name, dataSource);
                    });
            dataSourcesByClass.put(dataSourceClass, dataSource);
        });
    }

    public boolean contains(String dataSourceName) {
        return dataSourcesByName.containsKey(dataSourceName);
    }

    public <T extends DataSource> T get(String dataSourceName) {
        DataSource<?, ?> dataSource = dataSourcesByName.get(dataSourceName);
        if (null == dataSource) {
            throw DataSourceNotFound.exception(DATA_SOURCE_NOT_FOUND_BY_NAME.getMessage(dataSourceName));
        }
        return (T) dataSourcesByName.get(dataSourceName);
    }

    public Stream<String> names() {
        return dataSourcesByName.keySet().stream();
    }

}
