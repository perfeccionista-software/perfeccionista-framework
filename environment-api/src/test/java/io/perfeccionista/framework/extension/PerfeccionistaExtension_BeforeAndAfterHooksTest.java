package io.perfeccionista.framework.extension;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.UseEnvironmentConfiguration;
import io.perfeccionista.framework.extension.configurations.TestClassLocalEnvironmentConfiguration;
import io.perfeccionista.framework.service.Service;

import static org.mockito.Mockito.mock;

@ExtendWith(PerfeccionistaExtension.class)
@UseEnvironmentConfiguration(TestClassLocalEnvironmentConfiguration.class)
public class PerfeccionistaExtension_BeforeAndAfterHooksTest {

    @BeforeEach
    void setUp(Environment environment) {
        Assertions.assertNotNull(environment);
        Service beforeMethodService = mock(BeforeMethodService.class);
        environment.register(BeforeMethodService.class, beforeMethodService);
    }

    @Test
    void environmentInjectionTest(Environment environment) {
        Assertions.assertNotNull(environment);
        Assertions.assertTrue(environment.getService(BeforeMethodService.class).isPresent());
        Service testMethodService = mock(TestMethodService.class);
        environment.register(TestMethodService.class, testMethodService);
    }

    @AfterEach
    void tearDown(Environment environment) {
        Assertions.assertNotNull(environment);
        Assertions.assertTrue(environment.getService(BeforeMethodService.class).isPresent());
        Assertions.assertTrue(environment.getService(TestMethodService.class).isPresent());
    }

    private abstract static class BeforeMethodService implements Service {}
    private abstract static class TestMethodService implements Service {}

}
