package io.perfeccionista.framework.fixture;

import io.perfeccionista.framework.exceptions.RegisterDuplicate;
import io.perfeccionista.framework.exceptions.attachments.StringAttachmentEntry;
import io.perfeccionista.framework.name.Name;
import org.jetbrains.annotations.NotNull;
import org.junit.platform.commons.util.ClassFilter;
import org.junit.platform.commons.util.ReflectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.FIXTURE_REGISTER_BY_NAME_DUPLICATE;

public class DefaultFixtureServiceConfiguration implements FixtureServiceConfiguration {

    protected Set<String> packagesToScan;

    public DefaultFixtureServiceConfiguration() {
        this.packagesToScan = new HashSet<>();
    }

    public DefaultFixtureServiceConfiguration(@NotNull Set<String> packagesToScan) {
        this.packagesToScan = packagesToScan;
    }

    @Override
    public Map<String, Class<? extends Fixture<?, ?>>> getFixtureClasses() {
        Map<String, Class<? extends Fixture<?, ?>>> fixtures = new HashMap<>();

        List<Class<? extends Fixture<?, ?>>> foundFixtureClasses = packagesToScan.stream()
                .map(packageName -> {
                    //noinspection unchecked
                    List<Class<? extends Fixture<?, ?>>> classesInPackage = ReflectionUtils
                            .findAllClassesInPackage(packageName, ClassFilter.of(Fixture.class::isAssignableFrom)).stream()
                            .map(processedFixtureClass -> (Class<? extends Fixture<?, ?>>) processedFixtureClass)
                            .collect(Collectors.toList());
                    return classesInPackage;
                })
                .reduce(new ArrayList<>(), (lastClassesSet, processedClassesSet) -> {
                    lastClassesSet.addAll(processedClassesSet);
                    return lastClassesSet;
                });

        foundFixtureClasses.stream()
                .distinct()
                .forEach(processedClass -> Arrays
                        .stream(processedClass.getDeclaredAnnotationsByType(Name.class))
                        .forEach(fixtureNameAnnotation -> {
                            String fixtureName = fixtureNameAnnotation.value();
                            if (fixtures.containsKey(fixtureName)) {
                                throw RegisterDuplicate.exception(FIXTURE_REGISTER_BY_NAME_DUPLICATE.getMessage(fixtureName))
                                        .addLastAttachmentEntry(StringAttachmentEntry.of("First fixture class with name " + fixtureName,
                                                processedClass.getCanonicalName()))
                                        .addLastAttachmentEntry(StringAttachmentEntry.of("Second fixture class with name " + fixtureName,
                                                fixtures.get(fixtureName).getCanonicalName()));
                            }
                            fixtures.put(fixtureName, processedClass);
                        }));

        return fixtures;
    }

    @Override
    public boolean isRevertFixtures() {
        return true;
    }

}
