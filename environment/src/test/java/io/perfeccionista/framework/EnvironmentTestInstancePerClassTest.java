package io.perfeccionista.framework;

import io.perfeccionista.framework.services.TestService1;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import static org.mockito.Mockito.mock;

@TestInstance(Lifecycle.PER_CLASS)
public class EnvironmentTestInstancePerClassTest extends AbstractParallelTest {

    @BeforeEach
    void beforeEach() {
        Environment.createForCurrentThread(DefaultEnvironmentConfiguration.class)
                .init();
    }

    @AfterEach
    void afterEach() {
        Environment.finalizeForCurrentThread();
    }

    @Test
    void environmentTestOne() {
        Environment environment = Environment.getForCurrentThread();
        Assertions.assertNotNull(environment, "Environment is not initialized");
        Assertions.assertEquals(4, environment.getServices().count(), "The environment was initialized incorrectly");
        environment.register(TestService1.class, mock(TestService1.class));
    }

    @Test
    void environmentTestTwo() {
        Environment environment = Environment.getForCurrentThread();
        Assertions.assertNotNull(environment, "Environment is not initialized");
        Assertions.assertEquals(4, environment.getServices().count(), "The environment was initialized incorrectly");
        environment.register(TestService1.class, mock(TestService1.class));
    }

    @Test
    void environmentTestThree() {
        Environment environment = Environment.getForCurrentThread();
        Assertions.assertNotNull(environment, "Environment is not initialized");
        Assertions.assertEquals(4, environment.getServices().count(), "The environment was initialized incorrectly");
        environment.register(TestService1.class, mock(TestService1.class));
    }

    @Test
    void environmentTestFour() {
        Environment environment = Environment.getForCurrentThread();
        Assertions.assertNotNull(environment, "Environment is not initialized");
        Assertions.assertEquals(4, environment.getServices().count(), "The environment was initialized incorrectly");
        environment.register(TestService1.class, mock(TestService1.class));
    }

    @Test
    void environmentTestFive() {
        Environment environment = Environment.getForCurrentThread();
        Assertions.assertNotNull(environment, "Environment is not initialized");
        Assertions.assertEquals(4, environment.getServices().count(), "The environment was initialized incorrectly");
        environment.register(TestService1.class, mock(TestService1.class));
    }

    @Test
    void environmentTestSix() {
        Environment environment = Environment.getForCurrentThread();
        Assertions.assertNotNull(environment, "Environment is not initialized");
        Assertions.assertEquals(4, environment.getServices().count(), "The environment was initialized incorrectly");
        environment.register(TestService1.class, mock(TestService1.class));
    }

    @Test
    void environmentTestSeven() {
        Environment environment = Environment.getForCurrentThread();
        Assertions.assertNotNull(environment, "Environment is not initialized");
        Assertions.assertEquals(4, environment.getServices().count(), "The environment was initialized incorrectly");
        environment.register(TestService1.class, mock(TestService1.class));
    }

    @Test
    void environmentTestEight() {
        Environment environment = Environment.getForCurrentThread();
        Assertions.assertNotNull(environment, "Environment is not initialized");
        Assertions.assertEquals(4, environment.getServices().count(), "The environment was initialized incorrectly");
        environment.register(TestService1.class, mock(TestService1.class));
    }

    @Test
    void environmentTestNine() {
        Environment environment = Environment.getForCurrentThread();
        Assertions.assertNotNull(environment, "Environment is not initialized");
        Assertions.assertEquals(4, environment.getServices().count(), "The environment was initialized incorrectly");
        environment.register(TestService1.class, mock(TestService1.class));
    }

    @Test
    void environmentTestTen() {
        Environment environment = Environment.getForCurrentThread();
        Assertions.assertNotNull(environment, "Environment is not initialized");
        Assertions.assertEquals(4, environment.getServices().count(), "The environment was initialized incorrectly");
        environment.register(TestService1.class, mock(TestService1.class));
    }
}
