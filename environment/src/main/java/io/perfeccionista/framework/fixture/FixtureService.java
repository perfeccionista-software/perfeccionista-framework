package io.perfeccionista.framework.fixture;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.exceptions.DataSourceNotFoundException;
import io.perfeccionista.framework.exceptions.FixtureNotFoundException;
import org.jetbrains.annotations.NotNull;
import io.perfeccionista.framework.exceptions.IncorrectServiceConfigurationException;
import io.perfeccionista.framework.service.Service;
import io.perfeccionista.framework.service.ServiceConfiguration;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Optional;

import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.CHECK_CONFIGURATION_NOT_VALID;
import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.DATA_SOURCE_NOT_FOUND_BY_CLASS;
import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.FIXTURE_NOT_FOUND;

public class FixtureService implements Service {

    protected FixtureServiceConfiguration configuration;
    protected Deque<Fixture<?>> executedFixtures = new ArrayDeque<>();

    @Override
    public void init(@NotNull Environment environment, @NotNull ServiceConfiguration serviceConfiguration) {
        configuration = validate(serviceConfiguration);
    }

    @NotNull
    public <T> Optional<T> executeFixture(@NotNull Fixture<T> fixture) {
        executedFixtures.push(fixture);
        return fixture.execute();
    }

    @NotNull
    public <T> Fixture<T> get(@NotNull String name) {
        Fixture<T> fixture = configuration.findFixtureByName(name);
        if (fixture == null) {
            throw new FixtureNotFoundException(FIXTURE_NOT_FOUND.getMessage(name));
        }
        return fixture;
    }

    @Override
    public void afterTest() {
        if (configuration.isRevertFixtures()) {
            while (!executedFixtures.isEmpty()) {
                executedFixtures.pop().revert();
            }
        }
    }

    protected FixtureServiceConfiguration validate(ServiceConfiguration configuration) {
        if (configuration instanceof FixtureServiceConfiguration) {
            return (FixtureServiceConfiguration) configuration;
        }
        throw new IncorrectServiceConfigurationException(
                CHECK_CONFIGURATION_NOT_VALID.getMessage(configuration.getClass().getCanonicalName(), this.getClass().getCanonicalName()));
    }

}
