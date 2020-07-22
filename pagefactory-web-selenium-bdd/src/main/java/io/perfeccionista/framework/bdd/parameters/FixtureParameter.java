package io.perfeccionista.framework.bdd.parameters;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.bdd.parameters.datatable.entries.FixtureEntry;
import io.perfeccionista.framework.fixture.Fixture;
import org.jetbrains.annotations.NotNull;

public interface FixtureParameter<T> extends BddStepParameter {

    @NotNull
    Fixture<T> getFixtureInstance(Environment environment);

    @NotNull
    Fixture<T> getFixtureInstance(Environment environment, FixtureEntry fixtureParameters);

}
