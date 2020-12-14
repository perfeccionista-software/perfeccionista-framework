package io.perfeccionista.framework.cucumber.parameters;

import io.perfeccionista.framework.cucumber.parameters.datatable.entries.FixtureEntry;
import io.perfeccionista.framework.fixture.Fixture;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface FixtureParameter<S, T> extends CucumberStepParameter {

    @NotNull Fixture<S, T> getFixtureInstance();

    @NotNull Fixture<S, T> getFixtureInstance(@NotNull List<FixtureEntry> fixtureParameterEntries);

}
