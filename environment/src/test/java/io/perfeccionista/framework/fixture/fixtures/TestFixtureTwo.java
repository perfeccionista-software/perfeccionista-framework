package io.perfeccionista.framework.fixture.fixtures;

import io.perfeccionista.framework.fixture.Fixture;
import io.perfeccionista.framework.fixture.FixtureSetUpResult;
import io.perfeccionista.framework.fixture.FixtureTearDownResult;
import io.perfeccionista.framework.name.Name;
import org.jetbrains.annotations.NotNull;

@Name("Test fixture two")
public class TestFixtureTwo implements Fixture<String, Void> {

    @Override
    public @NotNull FixtureSetUpResult<String> setUp() {
        return FixtureSetUpResult.of("Success");
    }

    @Override
    public @NotNull FixtureTearDownResult<Void> tearDown() {
        return FixtureTearDownResult.empty();
    }

}
