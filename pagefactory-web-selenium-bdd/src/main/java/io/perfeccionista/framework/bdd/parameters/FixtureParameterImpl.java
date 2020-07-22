package io.perfeccionista.framework.bdd.parameters;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.bdd.parameters.datatable.entries.FixtureEntry;
import io.perfeccionista.framework.fixture.Fixture;
import io.perfeccionista.framework.fixture.FixtureService;
import org.jetbrains.annotations.NotNull;

public class FixtureParameterImpl<T> implements FixtureParameter<T> {

    private String rawInput;

    public FixtureParameterImpl(String fixtureName) {
        this.rawInput = fixtureName;
    }

    @Override
    public @NotNull Fixture<T> getFixtureInstance(Environment environment) {
        return environment.getService(FixtureService.class).get(rawInput);
    }

    @Override
    public @NotNull Fixture<T> getFixtureInstance(Environment environment, FixtureEntry fixtureParameters) {
        return environment.getService(FixtureService.class).get(rawInput);
    }

    @Override
    public String getRaw() {
        return rawInput;
    }
}
