package io.perfeccionista.framework.fixture;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.exceptions.ConstructorNotFoundException;
import io.perfeccionista.framework.exceptions.FixtureInstantiationException;
import io.perfeccionista.framework.exceptions.RegisterDuplicateException;
import io.perfeccionista.framework.name.Name;
import io.perfeccionista.framework.utils.ReflectionUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.platform.commons.util.AnnotationUtils;
import org.junit.platform.commons.util.ClassFilter;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.FIXTURE_INSTANTIATE_FAILED;
import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.FIXTURE_NAME_DUPLICATE;
import static org.junit.platform.commons.util.ReflectionUtils.findAllClassesInPackage;

public class DefaultFixtureServiceConfiguration implements FixtureServiceConfiguration {
    private Environment environment = null;
    private Set<Class<? extends Fixture<?>>> availableFixtureClasses = null;
    private Map<String, Class<? extends Fixture<?>>> fixtureClassesByName = null;

    @Override
    public void init(@NotNull Environment environment) {
        this.environment = environment;
    }

    @Override
    public boolean isRevertFixtures() {
        return true;
    }

    @Override
    public @Nullable <T> Fixture<T> findFixtureByName(@NotNull String name) {
        scan();
        if (fixtureClassesByName.containsKey(name)) {
            try {
                Class<? extends Fixture<?>> fixtureClass = fixtureClassesByName.get(name);
                try {
                    return (Fixture<T>) ReflectionUtils.getConstructor(fixtureClass, Environment.class).newInstance(environment);
                } catch (ConstructorNotFoundException ex) {
                    return (Fixture<T>) ReflectionUtils.getConstructor(fixtureClass).newInstance();
                }
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                throw new FixtureInstantiationException(FIXTURE_INSTANTIATE_FAILED.getMessage(fixtureClassesByName.get(name)));
            }
        }
        return null;
    }

    private void scan() {
        if (availableFixtureClasses != null) {
            return;
        }

        availableFixtureClasses = new HashSet<>();
        fixtureClassesByName = new HashMap<>();

        Set<String> packagesToScan = new HashSet<>();

        // TODO Если над тестовым классом, в пропертях или еще каким-либо образом пользователь переопределил список пакетов в которых нужно искать - то использовать их
        packagesToScan.add(environment.getTestClass().getPackageName());

        packagesToScan
                .forEach(fixturePackage -> findAllClassesInPackage(fixturePackage, ClassFilter.of(Fixture.class::isAssignableFrom))
                        .stream()
                        .map(fixtureClass -> (Class<? extends Fixture<?>>) fixtureClass)
                        .forEach(fixtureClass -> {
                            availableFixtureClasses.add(fixtureClass);
                            List<Name> names = AnnotationUtils.findRepeatableAnnotations(fixtureClass, Name.class);
                            names.stream().map(Name::value).forEach(name -> {
                                if (fixtureClassesByName.containsKey(name)) {
                                    throw new RegisterDuplicateException(FIXTURE_NAME_DUPLICATE.getMessage(name, fixtureClass, fixtureClassesByName.get(name)));
                                }
                                fixtureClassesByName.put(name, fixtureClass);
                            });
                        }));
    }
}
