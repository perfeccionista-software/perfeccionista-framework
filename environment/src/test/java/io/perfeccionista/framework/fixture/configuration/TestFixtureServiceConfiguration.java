package io.perfeccionista.framework.fixture.configuration;

import io.perfeccionista.framework.fixture.Fixture;
import io.perfeccionista.framework.fixture.FixtureService;
import io.perfeccionista.framework.fixture.FixtureServiceConfiguration;
import io.perfeccionista.framework.fixture.implementations.MyFixture;
import io.perfeccionista.framework.service.Service;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class TestFixtureServiceConfiguration implements FixtureServiceConfiguration {

    @Override
    public boolean isRevertFixtures() {
        return false;
    }

    @Override
    public @Nullable <T> Fixture<T> findFixtureByName(@NotNull String name) {
        switch (name) {
            case "MyFixture":
                return (Fixture<T>) new MyFixture();
        }
        return null;
    }

    @Override
    public Optional<Class<? extends Service>> getImplementation() {
        return Optional.of(FixtureService.class);
    }
}
