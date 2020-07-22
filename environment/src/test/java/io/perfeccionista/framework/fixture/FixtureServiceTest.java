package io.perfeccionista.framework.fixture;


import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.UseEnvironmentConfiguration;
import io.perfeccionista.framework.extension.PerfeccionistaExtension;
import io.perfeccionista.framework.fixture.configuration.FixtureLocalEnvironmentConfiguration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(PerfeccionistaExtension.class)
@UseEnvironmentConfiguration(FixtureLocalEnvironmentConfiguration.class)
public class FixtureServiceTest {

    @Test
    void executeSimpleFixtureText(Environment environment) {
        FixtureService fixtureService = environment.getService(FixtureService.class);
        assertNotNull(fixtureService);
        Fixture<?> fixture = fixtureService.get("MyFixture");
        assertNotNull(fixture);
        assertEquals("MyResult", fixtureService.executeFixture(fixture).orElse(null));
    }

}
