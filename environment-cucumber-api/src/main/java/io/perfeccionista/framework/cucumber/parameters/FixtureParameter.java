package io.perfeccionista.framework.cucumber.parameters;

import io.perfeccionista.framework.fixture.Fixture;
import io.perfeccionista.framework.fixture.FixtureParameters;
import org.jetbrains.annotations.NotNull;

public interface FixtureParameter<S, T> extends CucumberStepDefinitionParameter {

    @NotNull Fixture<S, T> getFixtureInstance();

    @NotNull Fixture<S, T> getFixtureInstance(@NotNull FixtureParameters fixtureParameters);

}
