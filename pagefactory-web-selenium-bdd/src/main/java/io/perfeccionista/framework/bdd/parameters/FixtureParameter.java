package io.perfeccionista.framework.bdd.parameters;

import io.perfeccionista.framework.bdd.parameters.datatable.entries.FixtureEntry;
import io.perfeccionista.framework.fixture.Fixture;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface FixtureParameter<S, T> extends BddStepParameter {

    @NotNull Fixture<S, T> getFixtureInstance();

    @NotNull Fixture<S, T> getFixtureInstance(@NotNull List<FixtureEntry> fixtureParameterEntries);

}
