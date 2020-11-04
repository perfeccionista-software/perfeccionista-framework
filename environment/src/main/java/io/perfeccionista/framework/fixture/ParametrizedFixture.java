package io.perfeccionista.framework.fixture;

import org.jetbrains.annotations.NotNull;

public interface ParametrizedFixture<S, T> extends Fixture<S, T> {

    ParametrizedFixture<S, T> withParameters(@NotNull FixtureParameters fixtureParameters);

}
