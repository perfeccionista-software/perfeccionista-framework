package io.perfeccionista.framework.bdd.parameters;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.bdd.parameters.datatable.entries.FixtureEntry;
import io.perfeccionista.framework.fixture.Fixture;

public interface FixtureParameter<T> extends BddStepParameter {

    Fixture<T> getFixtureInstance(Environment environment);

    Fixture<T> getFixtureInstance(Environment environment, FixtureEntry fixtureParameters);

}
