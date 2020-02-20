package io.perfeccionista.framework.fixture;

import org.jetbrains.annotations.NotNull;
import io.perfeccionista.framework.exceptions.IncorrectServiceConfigurationException;
import io.perfeccionista.framework.service.Service;
import io.perfeccionista.framework.service.ServiceConfiguration;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Optional;

import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.CHECK_CONFIGURATION_NOT_VALID;

public class FixtureService implements Service {

    protected FixtureServiceConfiguration configuration;
    protected Deque<Fixture<?>> executedFixtures = new ArrayDeque<>();

    @Override
    public void init(@NotNull ServiceConfiguration serviceConfiguration) {
        configuration = validate(serviceConfiguration);
    }

    public <T> Optional<T> executeFixture(Fixture<T> fixture) {
        executedFixtures.push(fixture);
        return fixture.execute();
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
