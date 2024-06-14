package io.perfeccionista.framework;

import io.perfeccionista.framework.exceptions.impl.EnvironmentAlreadyInitializedException;
import io.perfeccionista.framework.services.TestService1;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

public class EnvironmentTestInstancePerMethodTest extends AbstractParallelTest {

    private final Environment environment = new Environment(DefaultEnvironmentConfiguration.class);

    @BeforeEach
    void beforeEach() {
        environment.init();
    }

    @AfterEach
    void afterEach() {
        environment.shutdown();
    }

    @Test
    void environmentAlreadyInitializedTest() {
        Assertions.assertThrows(EnvironmentAlreadyInitializedException.class, environment::init);
    }
    @Test
    void environmentTestOne() {
        Assertions.assertNotNull(environment, "Environment is not initialized");
        Assertions.assertEquals(4, environment.getServices().count(), "The environment was initialized incorrectly");
        environment.register(TestService1.class, mock(TestService1.class));
    }

    @Test
    void environmentTestTwo() {
        Assertions.assertNotNull(environment, "Environment is not initialized");
        Assertions.assertEquals(4, environment.getServices().count(), "The environment was initialized incorrectly");
        environment.register(TestService1.class, mock(TestService1.class));
    }

    @Test
    void environmentTestThree() {
        Assertions.assertNotNull(environment, "Environment is not initialized");
        Assertions.assertEquals(4, environment.getServices().count(), "The environment was initialized incorrectly");
        environment.register(TestService1.class, mock(TestService1.class));
    }

    @Test
    void environmentTestFour() {
        Assertions.assertNotNull(environment, "Environment is not initialized");
        Assertions.assertEquals(4, environment.getServices().count(), "The environment was initialized incorrectly");
        environment.register(TestService1.class, mock(TestService1.class));
    }

    @Test
    void environmentTestFive() {
        Assertions.assertNotNull(environment, "Environment is not initialized");
        Assertions.assertEquals(4, environment.getServices().count(), "The environment was initialized incorrectly");
        environment.register(TestService1.class, mock(TestService1.class));
    }

    @Test
    void environmentTestSix() {
        Assertions.assertNotNull(environment, "Environment is not initialized");
        Assertions.assertEquals(4, environment.getServices().count(), "The environment was initialized incorrectly");
        environment.register(TestService1.class, mock(TestService1.class));
    }

    @Test
    void environmentTestSeven() {
        Assertions.assertNotNull(environment, "Environment is not initialized");
        Assertions.assertEquals(4, environment.getServices().count(), "The environment was initialized incorrectly");
        environment.register(TestService1.class, mock(TestService1.class));
    }

    @Test
    void environmentTestEight() {
        Assertions.assertNotNull(environment, "Environment is not initialized");
        Assertions.assertEquals(4, environment.getServices().count(), "The environment was initialized incorrectly");
        environment.register(TestService1.class, mock(TestService1.class));
    }

    @Test
    void environmentTestNine() {
        Assertions.assertNotNull(environment, "Environment is not initialized");
        Assertions.assertEquals(4, environment.getServices().count(), "The environment was initialized incorrectly");
        environment.register(TestService1.class, mock(TestService1.class));
    }

    @Test
    void environmentTestTen() {
        Assertions.assertNotNull(environment, "Environment is not initialized");
        Assertions.assertEquals(4, environment.getServices().count(), "The environment was initialized incorrectly");
        environment.register(TestService1.class, mock(TestService1.class));
    }

}
