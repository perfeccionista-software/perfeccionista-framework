package io.perfeccionista.framework.fixture.fixtures;

import io.perfeccionista.framework.fixture.FixtureParameters;
import io.perfeccionista.framework.fixture.FixtureSetUpResult;
import io.perfeccionista.framework.fixture.FixtureTearDownResult;
import io.perfeccionista.framework.fixture.ParametrizedFixture;
import io.perfeccionista.framework.name.Name;
import org.jetbrains.annotations.NotNull;

@Name("Test fixture three")
public class TestFixtureThree implements ParametrizedFixture<Long, Boolean> {

    private FixtureParameters fixtureParameters;

    @Override
    public @NotNull FixtureSetUpResult<Long> setUp() {
        return FixtureSetUpResult.of(fixtureParameters.get("Long value", Long.class));
    }

    @Override
    public @NotNull FixtureTearDownResult<Boolean> tearDown() {
        return FixtureTearDownResult.of(true);
    }

    @Override
    public ParametrizedFixture<Long, Boolean> withParameters(@NotNull FixtureParameters fixtureParameters) {
        this.fixtureParameters = fixtureParameters;
        return this;
    }

}
