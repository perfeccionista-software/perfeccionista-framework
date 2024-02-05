package io.perfeccionista.framework.fixture;

import io.perfeccionista.framework.service.ServiceConfiguration;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

// TODO: Подумать что можно сделать с процессорами результатов setUp/tearDown.
public interface FixtureServiceConfiguration extends ServiceConfiguration {

    @NotNull Map<String, Class<? extends Fixture<?, ?>>> getNamedFixtureClasses();

    @NotNull FixtureResultProcessor getFixtureResultProcessor();

    boolean isRevertFixtures();

}
