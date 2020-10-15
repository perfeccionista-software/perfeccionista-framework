package io.perfeccionista.framework.bdd.parameters;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.bdd.parameters.datatable.entries.FixtureEntry;
import io.perfeccionista.framework.fixture.Fixture;

public interface FixtureParameter<S, T> extends BddStepParameter {

    Fixture<S, T> getFixtureInstance(Environment environment);

    Fixture<S, T> getFixtureInstance(Environment environment, FixtureEntry fixtureParameters);

}
