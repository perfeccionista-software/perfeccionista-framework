package io.perfeccionista.framework.fixture;

import io.perfeccionista.framework.Environment;
import org.jetbrains.annotations.NotNull;
import io.perfeccionista.framework.exceptions.IncorrectServiceConfiguration;
import io.perfeccionista.framework.service.Service;
import io.perfeccionista.framework.service.ServiceConfiguration;

import java.util.ArrayDeque;
import java.util.Deque;

import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.CHECK_CONFIGURATION_NOT_VALID;

public class FixtureService implements Service {

    protected FixtureServiceConfiguration configuration;
    protected Deque<Fixture<?, ?>> executedFixtures = new ArrayDeque<>();

    @Override
    public void init(@NotNull Environment environment, @NotNull ServiceConfiguration serviceConfiguration) {
        configuration = validate(serviceConfiguration);
    }

    public <S, T> FixtureSetUpResult<S> executeFixture(Fixture<S, T> fixture) {
        executedFixtures.push(fixture);
        return fixture
                .setUp()
                .process();
    }

    @Override
    public void afterTest() {
        if (configuration.isRevertFixtures()) {
            while (!executedFixtures.isEmpty()) {
                executedFixtures.pop()
                        .tearDown()
                        .process();
            }
        }
    }

    protected FixtureServiceConfiguration validate(ServiceConfiguration configuration) {
        if (configuration instanceof FixtureServiceConfiguration) {
            return (FixtureServiceConfiguration) configuration;
        }
        throw IncorrectServiceConfiguration.exception(
                CHECK_CONFIGURATION_NOT_VALID.getMessage(configuration.getClass().getCanonicalName(), this.getClass().getCanonicalName()));
    }

}
