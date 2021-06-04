package io.perfeccionista.framework.fixture;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.exceptions.RegisterDuplicate;
import io.perfeccionista.framework.exceptions.attachments.TextAttachmentEntry;
import io.perfeccionista.framework.name.Name;

import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.FIXTURE_REGISTER_BY_NAME_DUPLICATE;
import static io.perfeccionista.framework.utils.PackageUtils.validatePackageSet;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.findAllClasses;

public class DefaultFixtureServiceConfiguration implements FixtureServiceConfiguration {

    @Override
    public Map<String, Class<? extends Fixture<?, ?>>> getFixtureClasses() {
        Map<String, Class<? extends Fixture<?, ?>>> fixtureClasses = new HashMap<>();
        Set<String> packagesToScan = Environment.getCurrent()
                .getEnvironmentConfiguration()
                .getScanPackages();
        //noinspection unchecked
        findAllClasses(validatePackageSet(packagesToScan), Fixture.class)
                .stream()
                .filter(fixtureClass -> !Modifier.isAbstract(fixtureClass.getModifiers())
                        && !fixtureClass.isInterface()
                        && !fixtureClass.isEnum())
                .map(processedClass -> (Class<? extends Fixture<?, ?>>) processedClass)
                .forEach(processedClass -> Arrays
                        .stream(processedClass.getDeclaredAnnotationsByType(Name.class))
                        .forEach(fixtureNameAnnotation -> {
                            String fixtureName = fixtureNameAnnotation.value();
                            if (fixtureClasses.containsKey(fixtureName)) {
                                throw RegisterDuplicate.exception(FIXTURE_REGISTER_BY_NAME_DUPLICATE.getMessage(fixtureName))
                                        .addLastAttachmentEntry(TextAttachmentEntry.of("First fixture class with name " + fixtureName,
                                                processedClass.getCanonicalName()))
                                        .addLastAttachmentEntry(TextAttachmentEntry.of("Second fixture class with name " + fixtureName,
                                                fixtureClasses.get(fixtureName).getCanonicalName()));
                            }
                            fixtureClasses.put(fixtureName, processedClass);
                        }));

        return fixtureClasses;
    }

    @Override
    public boolean isRevertFixtures() {
        return true;
    }

}
