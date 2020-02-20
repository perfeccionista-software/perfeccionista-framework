package io.perfeccionista.framework.fixture;

import io.perfeccionista.framework.service.ServiceConfiguration;

public interface FixtureServiceConfiguration extends ServiceConfiguration {

    boolean isRevertFixtures();

}
