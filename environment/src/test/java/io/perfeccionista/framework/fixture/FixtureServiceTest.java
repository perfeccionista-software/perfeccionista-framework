package io.perfeccionista.framework.fixture;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.AbstractParallelTestWithEnvironment;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class FixtureServiceTest extends AbstractParallelTestWithEnvironment {

    @Test
    void fixtureServiceInitializationTest(FixtureService fixtureService) {
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
                            () -> assertNotNull(testFixtureOneSetUpResult.getResult()),
                            () -> assertEquals(777, testFixtureOneSetUpResult.getNotNullResult())
                    );
                },
                () -> {
                    FixtureSetUpResult<String> testFixtureTwoSetUpResult = fixtureService
                            .executeFixture("Test fixture two");
                    assertAll(
                            () -> assertNotNull(testFixtureTwoSetUpResult.getResult()),
                            () -> assertEquals("Success", testFixtureTwoSetUpResult.getNotNullResult())
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
                () -> assertNotNull(testFixtureThreeSetUpResult.getResult()),
                () -> assertEquals(45746L, testFixtureThreeSetUpResult.getResult())
        );
    }

}
