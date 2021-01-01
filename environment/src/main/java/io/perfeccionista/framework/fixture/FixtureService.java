package io.perfeccionista.framework.fixture;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.exceptions.FixtureNotFound;
import io.perfeccionista.framework.exceptions.FixtureNotParametrized;
import io.perfeccionista.framework.service.DefaultServiceConfiguration;
import org.jetbrains.annotations.NotNull;
import io.perfeccionista.framework.exceptions.IncorrectServiceConfiguration;
import io.perfeccionista.framework.service.Service;
import io.perfeccionista.framework.service.ServiceConfiguration;
import org.junit.platform.commons.util.ReflectionUtils;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.CHECK_CONFIGURATION_NOT_VALID;
import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.FIXTURE_NOT_FOUND;
import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.FIXTURE_NOT_PARAMETRIZED;

@DefaultServiceConfiguration(DefaultFixtureServiceConfiguration.class)
public class FixtureService implements Service {

    protected Environment environment;
    protected FixtureServiceConfiguration configuration;
    protected Map<String, Class<? extends Fixture<?, ?>>> fixtureClasses;

    protected Deque<Fixture<?, ?>> executedFixtures = new ArrayDeque<>();

    @Override
    public void init(@NotNull Environment environment, @NotNull ServiceConfiguration configuration) {
        this.environment = environment;
        this.configuration = validate(configuration);
        this.fixtureClasses = this.configuration.getFixtureClasses();
    }

    public <S, T> @NotNull Fixture<S, T> getFixture(@NotNull String fixtureName) {
        Class<? extends Fixture<?, ?>> fixtureClass = fixtureClasses.get(fixtureName);
        if (Objects.isNull(fixtureClass)) {
            throw FixtureNotFound.exception(FIXTURE_NOT_FOUND.getMessage(fixtureName));
        }
        Fixture<?, ?> fixtureInstance = ReflectionUtils.newInstance(fixtureClass);
        // TODO: Check fixture parametrized types
        return (Fixture<S, T>) fixtureInstance;
    }

    public <S, T> @NotNull ParametrizedFixture<S, T> getParametrizedFixture(@NotNull String fixtureName,
                                                                            @NotNull FixtureParameters fixtureParameters) {
        Fixture<S, T> fixtureInstance = getFixture(fixtureName);
        if (fixtureInstance instanceof ParametrizedFixture) {
            return ((ParametrizedFixture<S, T>) fixtureInstance).withParameters(fixtureParameters);
        }
        throw FixtureNotParametrized.exception(FIXTURE_NOT_PARAMETRIZED.getMessage(fixtureName));
    }

    public <S, T> @NotNull FixtureSetUpResult<S> executeFixture(@NotNull Fixture<S, T> fixture) {
        executedFixtures.push(fixture);
        return fixture
                .setUp()
                .process();
    }

    public <S> @NotNull FixtureSetUpResult<S> executeFixture(@NotNull String fixtureName) {
        return executeFixture(getFixture(fixtureName));
    }

    public <S> @NotNull FixtureSetUpResult<S> executeFixture(@NotNull String fixtureName,
                                                             @NotNull FixtureParameters fixtureParameters) {
        return executeFixture(getParametrizedFixture(fixtureName, fixtureParameters));
    }

    public boolean containsName(@NotNull String fixtureName) {
        return fixtureClasses.containsKey(fixtureName);
    }

    public Stream<Class<? extends Fixture<?, ?>>> stream() {
        return fixtureClasses.values().stream().distinct();
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
