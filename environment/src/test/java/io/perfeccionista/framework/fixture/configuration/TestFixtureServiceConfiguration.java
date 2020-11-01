package io.perfeccionista.framework.fixture.configuration;

import io.perfeccionista.framework.fixture.DefaultFixtureServiceConfiguration;

import java.util.Set;

public class TestFixtureServiceConfiguration extends DefaultFixtureServiceConfiguration {

    public TestFixtureServiceConfiguration() {
        super(Set.of("io.perfeccionista.framework.fixture"));
    }

}
