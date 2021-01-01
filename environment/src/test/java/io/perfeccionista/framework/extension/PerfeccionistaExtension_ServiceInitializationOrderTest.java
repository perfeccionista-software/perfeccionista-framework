package io.perfeccionista.framework.extension;

import io.perfeccionista.framework.AbstractParallelTestWithEnvironment;
import org.junit.jupiter.api.Test;
import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.UseEnvironment;
import io.perfeccionista.framework.extension.configurations.TestClassLocalEnvironmentConfiguration;
import io.perfeccionista.framework.extension.services.AbstractTestService;
import io.perfeccionista.framework.extension.services.TestService1;
import io.perfeccionista.framework.extension.services.TestService2;
import io.perfeccionista.framework.extension.services.TestService3;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@UseEnvironment(TestClassLocalEnvironmentConfiguration.class)
class PerfeccionistaExtension_ServiceInitializationOrderTest extends AbstractParallelTestWithEnvironment {

    @Test
    void serviceInitializationOrderTest(Environment environment) {
        assertAll(
                () -> assertEquals(3, environment.getServices().count()),
                () -> {
                    long service1InitializationTime = environment.getService(TestService1.class).getInitializationTime();
                    long service2InitializationTime = environment.getService(TestService2.class).getInitializationTime();
                    long service3InitializationTime = environment.getService(TestService3.class).getInitializationTime();
                    assertAll(
                            () -> assertTrue(service1InitializationTime < service2InitializationTime),
                            () -> assertTrue(service2InitializationTime < service3InitializationTime)
                    );
                }
        );
    }

    @Test
    void serviceBeforeTestExecutedTest(Environment environment) {
        assertEquals(3, environment.getServices().filter(service -> ((AbstractTestService) service).isBeforeTestExecuted()).count());
    }

}
