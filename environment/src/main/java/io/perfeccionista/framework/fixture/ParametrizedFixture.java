package io.perfeccionista.framework.fixture;

import org.jetbrains.annotations.NotNull;

public interface ParametrizedFixture<S, T, P extends FixtureParameters> extends Fixture<S, T> {

    ParametrizedFixture<S, T, P> withParameters(@NotNull P fixtureParameters);

}
