package io.perfeccionista.framework.cucumber.parameters;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.fixture.Fixture;
import io.perfeccionista.framework.fixture.FixtureParameters;
import io.perfeccionista.framework.fixture.FixtureService;
import io.perfeccionista.framework.fixture.ParametrizedFixture;
import org.jetbrains.annotations.NotNull;

public class FixtureParameterImpl<S, T> implements FixtureParameter<S, T> {

    private final Environment environment;
    private final String rawInput;

    public FixtureParameterImpl(Environment environment, String fixtureName) {
        this.environment = environment;
        this.rawInput = fixtureName;
    }

    @Override
    public @NotNull Fixture<S, T> getFixtureInstance() {
        return environment.getService(FixtureService.class)
                .getFixture(rawInput);
    }

    @Override
    public @NotNull ParametrizedFixture<S, T> getFixtureInstance(@NotNull FixtureParameters fixtureParameters) {
        return environment.getService(FixtureService.class)
                .getParametrizedFixture(rawInput, fixtureParameters);
    }

    @Override
    public @NotNull String getRaw() {
        return rawInput;
    }

}
