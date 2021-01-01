package io.perfeccionista.framework.datasource;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.exceptions.RegisterDuplicate;
import io.perfeccionista.framework.name.Name;

import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.DATA_SOURCE_REGISTER_BY_NAME_DUPLICATE;
import static io.perfeccionista.framework.utils.PackageUtils.validatePackageSet;
import static io.perfeccionista.framework.utils.ReflectionUtils.findAllClasses;
import static org.junit.platform.commons.util.AnnotationUtils.findRepeatableAnnotations;
import static org.junit.platform.commons.util.ReflectionUtils.newInstance;

// Возвращает все
public class DefaultDataSourceServiceConfiguration implements DataSourceServiceConfiguration {

    @Override
    public Map<String, DataSource<?, ?>> getDataSources() {
        Set<String> packagesToScan = Environment.getCurrent()
                .getEnvironmentConfiguration()
                .getScanPackages();
        Set<Class<? extends DataSource>> dataSourceClasses = findAllClasses(validatePackageSet(packagesToScan), DataSource.class)
                .stream()
                .filter(dataSourceClass -> !Modifier.isAbstract(dataSourceClass.getModifiers())
                        && !dataSourceClass.isInterface()
                        && !dataSourceClass.isEnum())
                .collect(Collectors.toSet());
        Map<String, DataSource<?, ?>> dataSourcesByName = new HashMap<>();
        dataSourceClasses.forEach(dataSourceClass -> {
            DataSource<?, ?> dataSourceInstance = newInstance(dataSourceClass);
            dataSourcesByName.put(dataSourceClass.getCanonicalName(), dataSourceInstance);
            findRepeatableAnnotations(dataSourceClass, Name.class).stream()
                    .map(Name::value)
                    .forEach(name -> {
                        if (dataSourcesByName.containsKey(name)) {
                            throw RegisterDuplicate.exception(DATA_SOURCE_REGISTER_BY_NAME_DUPLICATE.getMessage(name));
                        }
                        dataSourcesByName.put(name, dataSourceInstance);
                    });
        });
        return dataSourcesByName;
    }

    @Override
    public Optional<DataSource<?, ?>> getDefaultDataSource(Map<String, DataSource<?, ?>> dataSources) {
        return Optional.ofNullable(dataSources.get("stash"));
    }

}
