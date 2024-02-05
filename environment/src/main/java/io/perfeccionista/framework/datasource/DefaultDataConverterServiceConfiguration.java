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
import java.util.Properties;
import java.util.Set;

import static io.perfeccionista.framework.Environment.PERFECCIONISTA_PROPERTIES_FILE;
import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.DATA_CONVERTER_REGISTER_BY_NAME_DUPLICATE;
import static io.perfeccionista.framework.utils.AnnotationUtils.findRepeatableAnnotations;
import static io.perfeccionista.framework.utils.PackageUtils.validatePackageSet;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.findAllClasses;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.newInstance;

public class DefaultDataConverterServiceConfiguration implements DataConverterServiceConfiguration {

    protected static final String PERFECCIONISTA_DATA_CONVERTERS_SCAN_PACKAGES = "perfeccionista.dataconverters.scanPackages";

    protected static volatile boolean cacheReady = false;
    protected static Map<String, Class<? extends DataConverter<?, ?>>> cachedDataConverterClasses = new HashMap<>();
    protected static Properties perfeccionistaProperties;
    protected static Properties systemProperties;

    protected Map<String, DataConverter<?, ?>> dataConvertersByName = new HashMap<>();

    @Override
    public Map<String, DataConverter<?, ?>> getNamedDataConverters() {
        synchronized (DefaultDataConverterServiceConfiguration.class) {
            if (!cacheReady) {
                readProperties();
                findAllClasses(validatePackageSet(getDataConverterScanPackages()), DataConverter.class)
                        .stream()
                        .filter(dataConverterClass -> !Modifier.isAbstract(dataConverterClass.getModifiers())
                                && !dataConverterClass.isInterface()
                                && !dataConverterClass.isEnum())
                        .map(processedClass -> (Class<? extends DataConverter<?, ?>>) processedClass)
                        .forEach(processedClass -> {
                            List<Name> names = findRepeatableAnnotations(processedClass, Name.class);
                            if (names.isEmpty()) {
                                String className = processedClass.getCanonicalName();
                                cachedDataConverterClasses.put(className, processedClass);
                            } else {
                                names.forEach(dataConverterNameAnnotation -> {
                                    String dataConverterName = dataConverterNameAnnotation.value();
                                    if (cachedDataConverterClasses.containsKey(dataConverterName)) {
                                        throw RegisterDuplicate.exception(DATA_CONVERTER_REGISTER_BY_NAME_DUPLICATE.getMessage(dataConverterName))
                                                .addLastAttachmentEntry(TextAttachmentEntry.of("First DataConverter class with name " + dataConverterName,
                                                        processedClass.getCanonicalName()))
                                                .addLastAttachmentEntry(TextAttachmentEntry.of("Second DataConverter class with name " + dataConverterName,
                                                        cachedDataConverterClasses.get(dataConverterName).getCanonicalName()));
                                    }
                                    cachedDataConverterClasses.put(dataConverterName, processedClass);
                                });
                            }
                        });
                cacheReady = true;
            }
        }
        cachedDataConverterClasses.forEach((name, dataConverterClass) -> {
            DataConverter<?, ?> dataConverterInstance = newInstance(dataConverterClass);
            dataConvertersByName.put(name, dataConverterInstance);
        });
        return dataConvertersByName;
    }

    protected @NotNull Set<String> getDataConverterScanPackages() {
        // Переменная окружения имеет самый высокий приоритет
        if (systemProperties.containsKey(PERFECCIONISTA_DATA_CONVERTERS_SCAN_PACKAGES)) {
            List<String> packages = Arrays.asList(systemProperties.getProperty(PERFECCIONISTA_DATA_CONVERTERS_SCAN_PACKAGES).split(","));
            return validatePackageSet(new HashSet<>(packages));
        }
        // Если переменная окружения не задана, то ищем переменную заданную в свойствах проекта
        if (perfeccionistaProperties.containsKey(PERFECCIONISTA_DATA_CONVERTERS_SCAN_PACKAGES)) {
            List<String> packages = Arrays.asList(perfeccionistaProperties.getProperty(PERFECCIONISTA_DATA_CONVERTERS_SCAN_PACKAGES).split(","));
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
