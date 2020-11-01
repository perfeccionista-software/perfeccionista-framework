package io.perfeccionista.framework.fixture.fixtures;

import io.perfeccionista.framework.fixture.Fixture;
import io.perfeccionista.framework.fixture.FixtureSetUpResult;
import io.perfeccionista.framework.fixture.FixtureTearDownResult;
import io.perfeccionista.framework.name.Name;
import org.jetbrains.annotations.NotNull;

@Name("Test fixture one")
public class TestFixtureOne implements Fixture<Integer, Boolean> {

    @Override
    public @NotNull FixtureSetUpResult<Integer> setUp() {
        return FixtureSetUpResult.of(777);
    }

    @Override
    public @NotNull FixtureTearDownResult<Boolean> tearDown() {
        return FixtureTearDownResult.of(true)
                .setResultProcessor(result -> {
                    if (!result) {
                        throw new RuntimeException("Fixture tearDown result is not true");
                    }
                });
    }

}
