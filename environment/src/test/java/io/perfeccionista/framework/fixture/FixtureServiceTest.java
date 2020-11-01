package io.perfeccionista.framework.fixture;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.UseEnvironmentConfiguration;
import io.perfeccionista.framework.extension.PerfeccionistaExtension;
import io.perfeccionista.framework.fixture.configuration.FixtureLocalEnvironmentConfiguration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(PerfeccionistaExtension.class)
@UseEnvironmentConfiguration(FixtureLocalEnvironmentConfiguration.class)
class FixtureServiceTest {

    @Test
    void fixtureServiceInitializationTest(Environment environment) {
        FixtureService fixtureService = environment.getService(FixtureService.class);
        assertNotNull(fixtureService);
        assertEquals(3, fixtureService.stream().count());
    }

    @Test
    void fixtureExecutionTest(Environment environment) {
        FixtureService fixtureService = environment.getService(FixtureService.class);

        FixtureSetUpResult<Integer> testFixtureOneSetUpResult = fixtureService
                .executeFixture("Test fixture one");
        assertTrue(testFixtureOneSetUpResult.getResult().isPresent());
        assertEquals(777, testFixtureOneSetUpResult.getResult().get());

        FixtureSetUpResult<String> testFixtureTwoSetUpResult = fixtureService
                .executeFixture("Test fixture two");
        assertTrue(testFixtureTwoSetUpResult.getResult().isPresent());
        assertEquals("Success", testFixtureTwoSetUpResult.getResult().get());
    }

    @Test
    void parametrizedFixtureExecutionTest(Environment environment) {
        FixtureService fixtureService = environment.getService(FixtureService.class);

        FixtureParameters fixtureParameters = FixtureParameters.builder()
                .addParameter("Long value", 45746L);
        FixtureSetUpResult<Long> testFixtureThreeSetUpResult = fixtureService
                .executeFixture("Test fixture three", fixtureParameters);
        assertTrue(testFixtureThreeSetUpResult.getResult().isPresent());
        assertEquals(45746L, testFixtureThreeSetUpResult.getResult().get());
    }

}
