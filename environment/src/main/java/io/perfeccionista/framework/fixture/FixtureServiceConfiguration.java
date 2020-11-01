package io.perfeccionista.framework.fixture;

import io.perfeccionista.framework.service.ServiceConfiguration;

import java.util.Map;

public interface FixtureServiceConfiguration extends ServiceConfiguration {

    Map<String, Class<? extends Fixture<?, ?>>> getFixtureClasses();

    boolean isRevertFixtures();

}
