package io.perfeccionista.framework.fixture;

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
import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.FIXTURE_REGISTER_BY_NAME_DUPLICATE;
import static io.perfeccionista.framework.utils.AnnotationUtils.findRepeatableAnnotations;
import static io.perfeccionista.framework.utils.PackageUtils.validatePackageSet;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.findAllClasses;

public class DefaultFixtureServiceConfiguration implements FixtureServiceConfiguration {

    protected static final String PERFECCIONISTA_FIXTURES_SCAN_PACKAGES = "perfeccionista.fixtures.scanPackages";

    protected static volatile boolean cacheReady = false;
    protected static Map<String, Class<? extends Fixture<?, ?>>> cachedFixtureClasses = new HashMap<>();
    protected static Properties perfeccionistaProperties;
    protected static Properties systemProperties;

    @Override
    @NotNull public Map<String, Class<? extends Fixture<?, ?>>> getNamedFixtureClasses() {
        synchronized (DefaultFixtureServiceConfiguration.class) {
            if (!cacheReady) {
                readProperties();
                //noinspection unchecked
                findAllClasses(validatePackageSet(getFixtureScanPackages()), Fixture.class)
                        .stream()
                        .filter(fixtureClass -> !Modifier.isAbstract(fixtureClass.getModifiers())
                                && !fixtureClass.isInterface()
                                && !fixtureClass.isEnum())
                        .map(processedClass -> (Class<? extends Fixture<?, ?>>) processedClass)
                        .forEach(processedClass -> findRepeatableAnnotations(processedClass, Name.class)
                                .forEach(fixtureNameAnnotation -> {
                                    String fixtureName = fixtureNameAnnotation.value();
                                    if (cachedFixtureClasses.containsKey(fixtureName)) {
                                        throw RegisterDuplicate.exception(FIXTURE_REGISTER_BY_NAME_DUPLICATE.getMessage(fixtureName))
                                                .addLastAttachmentEntry(TextAttachmentEntry.of("First fixture class with name " + fixtureName,
                                                        processedClass.getCanonicalName()))
                                                .addLastAttachmentEntry(TextAttachmentEntry.of("Second fixture class with name " + fixtureName,
                                                        cachedFixtureClasses.get(fixtureName).getCanonicalName()));
                                    }
                                    cachedFixtureClasses.put(fixtureName, processedClass);
                                }));
                cacheReady = true;
            }
        }
        return cachedFixtureClasses;
    }

    @Override
    public @NotNull FixtureResultProcessor getFixtureResultProcessor() {
        return new DefaultFixtureResultProcessor();
    }

    protected @NotNull Set<String> getFixtureScanPackages() {
        // Переменная окружения имеет самый высокий приоритет
        if (systemProperties.containsKey(PERFECCIONISTA_FIXTURES_SCAN_PACKAGES)) {
            List<String> packages = Arrays.asList(systemProperties.getProperty(PERFECCIONISTA_FIXTURES_SCAN_PACKAGES).split(","));
            return validatePackageSet(new HashSet<>(packages));
        }
        // Если переменная окружения не задана, то ищем переменную заданную в свойствах проекта
        if (perfeccionistaProperties.containsKey(PERFECCIONISTA_FIXTURES_SCAN_PACKAGES)) {
            List<String> packages = Arrays.asList(perfeccionistaProperties.getProperty(PERFECCIONISTA_FIXTURES_SCAN_PACKAGES).split(","));
            return validatePackageSet(new HashSet<>(packages));
        }
        return Set.of();
    }

    protected void readProperties() {
        perfeccionistaProperties = FileUtils.readOptionalPropertyFileFromClasspath(PERFECCIONISTA_PROPERTIES_FILE)
                .orElse(new Properties());
        systemProperties = System.getProperties();
    }

    @Override
    public boolean isRevertFixtures() {
        return true;
    }

}
