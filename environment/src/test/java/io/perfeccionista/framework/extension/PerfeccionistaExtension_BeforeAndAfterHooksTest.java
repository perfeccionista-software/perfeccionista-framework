package io.perfeccionista.framework.extension;

import io.perfeccionista.framework.AbstractParallelTestWithEnvironment;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.UseEnvironment;
import io.perfeccionista.framework.extension.configurations.TestClassLocalEnvironmentConfiguration;
import io.perfeccionista.framework.service.Service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

@UseEnvironment(TestClassLocalEnvironmentConfiguration.class)
class PerfeccionistaExtension_BeforeAndAfterHooksTest extends AbstractParallelTestWithEnvironment {

    @BeforeEach
    void setUp(Environment environment) {
        assertNotNull(environment);
        Service beforeMethodService = mock(BeforeMethodService.class);
        environment.register(BeforeMethodService.class, beforeMethodService);
        assertNotNull(environment.getService(BeforeMethodService.class));
    }

    @Test
    void environmentInjectionTest(Environment environment) {
        assertAll(
                () -> assertNotNull(environment),
                () -> assertNotNull(environment.getService(BeforeMethodService.class))
        );
        Service testMethodService = mock(TestMethodService.class);
        environment.register(TestMethodService.class, testMethodService);
        assertNotNull(environment.getService(TestMethodService.class));
    }

    @AfterEach
    void tearDown(Environment environment) {
        assertAll(
                () -> assertNotNull(environment),
                () -> assertNotNull(environment.getService(BeforeMethodService.class)),
                () -> assertNotNull(environment.getService(TestMethodService.class))
        );
    }

    private abstract static class BeforeMethodService implements Service {}
    private abstract static class TestMethodService implements Service {}

}
