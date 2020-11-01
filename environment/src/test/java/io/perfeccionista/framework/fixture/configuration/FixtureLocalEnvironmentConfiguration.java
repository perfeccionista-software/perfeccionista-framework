package io.perfeccionista.framework.fixture.configuration;

import io.perfeccionista.framework.DefaultEnvironmentConfiguration;
import io.perfeccionista.framework.fixture.FixtureService;
import io.perfeccionista.framework.service.UseService;

@UseService(service = FixtureService.class, configuration = TestFixtureServiceConfiguration.class)
public class FixtureLocalEnvironmentConfiguration extends DefaultEnvironmentConfiguration {
}
