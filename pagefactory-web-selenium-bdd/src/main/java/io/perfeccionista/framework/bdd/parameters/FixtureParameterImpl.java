package io.perfeccionista.framework.bdd.parameters;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.bdd.parameters.datatable.entries.FixtureEntry;
import io.perfeccionista.framework.fixture.Fixture;

public class FixtureParameterImpl<S, T> implements FixtureParameter<S, T> {

    @Override
    public Fixture<S, T> getFixtureInstance(Environment environment) {
        return null;
    }

    @Override
    public Fixture<S, T> getFixtureInstance(Environment environment, FixtureEntry fixtureParameters) {
        return null;
    }

    @Override
    public String getRaw() {
        return null;
    }
}
