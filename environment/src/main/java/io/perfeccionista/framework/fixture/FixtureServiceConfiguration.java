package io.perfeccionista.framework.fixture;

import io.perfeccionista.framework.service.ServiceConfiguration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface FixtureServiceConfiguration extends ServiceConfiguration {

    boolean isRevertFixtures();

    @Nullable
    <T> Fixture<T> findFixtureByName(@NotNull String name);
}
