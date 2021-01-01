package io.perfeccionista.framework.fixture;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.AbstractParallelTestWithEnvironment;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FixtureServiceTest extends AbstractParallelTestWithEnvironment {

    @Test
    void fixtureServiceInitializationTest(Environment environment) {
        FixtureService fixtureService = environment.getService(FixtureService.class);
        assertAll(
                () -> assertNotNull(fixtureService),
                () -> assertEquals(3, fixtureService.stream().count())
        );
    }

    @Test
    void fixtureProcessingTest(Environment environment) {
        FixtureService fixtureService = environment.getService(FixtureService.class);
        assertAll(
                () -> {
                    FixtureSetUpResult<Integer> testFixtureOneSetUpResult = fixtureService
                            .executeFixture("Test fixture one");
                    assertAll(
                            () -> assertTrue(testFixtureOneSetUpResult.getResult().isPresent()),
                            () -> assertEquals(777, testFixtureOneSetUpResult.getResult().get())
                    );
                },
                () -> {
                    FixtureSetUpResult<String> testFixtureTwoSetUpResult = fixtureService
                            .executeFixture("Test fixture two");
                    assertAll(
                            () -> assertTrue(testFixtureTwoSetUpResult.getResult().isPresent()),
                            () -> assertEquals("Success", testFixtureTwoSetUpResult.getResult().get())
                    );
                }
        );
    }

    @Test
    void parametrizedFixtureProcessingTest(Environment environment) {
        FixtureService fixtureService = environment.getService(FixtureService.class);
        FixtureParameters fixtureParameters = FixtureParameters.builder()
                .addParameter("Long value", 45746L);
        FixtureSetUpResult<Long> testFixtureThreeSetUpResult = fixtureService
                .executeFixture("Test fixture three", fixtureParameters);
        assertAll(
                () -> assertTrue(testFixtureThreeSetUpResult.getResult().isPresent()),
                () -> assertEquals(45746L, testFixtureThreeSetUpResult.getResult().get())
        );
    }

}
