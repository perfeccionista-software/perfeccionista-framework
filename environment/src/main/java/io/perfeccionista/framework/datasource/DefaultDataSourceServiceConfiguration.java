package io.perfeccionista.framework.datasource;

import io.perfeccionista.framework.exceptions.RegisterDuplicate;
import io.perfeccionista.framework.exceptions.attachments.TextAttachmentEntry;
import io.perfeccionista.framework.name.Name;
import io.perfeccionista.framework.utils.FileUtils;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.Set;

import static io.perfeccionista.framework.Environment.PERFECCIONISTA_PROPERTIES_FILE;
import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.DATA_SOURCE_REGISTER_BY_NAME_DUPLICATE;
import static io.perfeccionista.framework.utils.AnnotationUtils.findRepeatableAnnotations;
import static io.perfeccionista.framework.utils.PackageUtils.validatePackageSet;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.findAllClasses;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.newInstance;

// Возвращает все
public class DefaultDataSourceServiceConfiguration implements DataSourceServiceConfiguration {

    protected static final String PERFECCIONISTA_DATA_SOURCE_SCAN_PACKAGES = "perfeccionista.datasources.scanPackages";

    protected static volatile boolean cacheReady = false;
    protected static Map<String, Class<? extends DataSource<?, ?>>> cachedDataSourceClasses = new HashMap<>();
    protected static Properties perfeccionistaProperties;
    protected static Properties systemProperties;

    protected Map<String, DataSource<?, ?>> dataSourcesByName = new HashMap<>();

    @Override
    public Optional<DataSource<?, ?>> getDefaultDataSource(Map<String, DataSource<?, ?>> dataSources) {
        return Optional.ofNullable(dataSources.get("stash"));
    }

    @Override
    public Map<String, DataSource<?, ?>> getNamedDataSources() {
        synchronized (DefaultDataConverterServiceConfiguration.class) {
            if (!cacheReady) {
                readProperties();
                findAllClasses(validatePackageSet(getDataSourceScanPackages()), DataSource.class)
                        .stream()
                        .filter(dataSourceClass -> !Modifier.isAbstract(dataSourceClass.getModifiers())
                                && !dataSourceClass.isInterface()
                                && !dataSourceClass.isEnum())
                        .map(processedClass -> (Class<? extends DataSource<?, ?>>) processedClass)
                        .forEach(processedClass -> {
                            List<Name> names = findRepeatableAnnotations(processedClass, Name.class);
                            if (names.isEmpty()) {
                                String className = processedClass.getCanonicalName();
                                cachedDataSourceClasses.put(className, processedClass);
                            } else {
                                names.forEach(dataSourceNameAnnotation -> {
                                    String dataSourceName = dataSourceNameAnnotation.value();
                                    if (cachedDataSourceClasses.containsKey(dataSourceName)) {
                                        throw RegisterDuplicate.exception(DATA_SOURCE_REGISTER_BY_NAME_DUPLICATE.getMessage(dataSourceName))
                                                .addLastAttachmentEntry(TextAttachmentEntry.of("First DataSource class with name " + dataSourceName,
                                                        processedClass.getCanonicalName()))
                                                .addLastAttachmentEntry(TextAttachmentEntry.of("Second DataSource class with name " + dataSourceName,
                                                        cachedDataSourceClasses.get(dataSourceName).getCanonicalName()));
                                    }
                                    cachedDataSourceClasses.put(dataSourceName, processedClass);
                                });
                            }
                        });
                cacheReady = true;
            }
        }
        cachedDataSourceClasses.forEach((name, dataSourceClass) -> {
            DataSource<?, ?> dataSourceInstance = newInstance(dataSourceClass);
            dataSourcesByName.put(name, dataSourceInstance);
        });
        return dataSourcesByName;
    }

    protected @NotNull Set<String> getDataSourceScanPackages() {
        // Переменная окружения имеет самый высокий приоритет
        if (systemProperties.containsKey(PERFECCIONISTA_DATA_SOURCE_SCAN_PACKAGES)) {
            List<String> packages = Arrays.asList(systemProperties.getProperty(PERFECCIONISTA_DATA_SOURCE_SCAN_PACKAGES).split(","));
            return validatePackageSet(new HashSet<>(packages));
        }
        // Если переменная окружения не задана, то ищем переменную заданную в свойствах проекта
        if (perfeccionistaProperties.containsKey(PERFECCIONISTA_DATA_SOURCE_SCAN_PACKAGES)) {
            List<String> packages = Arrays.asList(perfeccionistaProperties.getProperty(PERFECCIONISTA_DATA_SOURCE_SCAN_PACKAGES).split(","));
            return validatePackageSet(new HashSet<>(packages));
        }
        return Set.of();
    }

    protected void readProperties() {
        perfeccionistaProperties = FileUtils.readOptionalPropertyFileFromClasspath(PERFECCIONISTA_PROPERTIES_FILE)
                .orElse(new Properties());
        systemProperties = System.getProperties();
    }

}
