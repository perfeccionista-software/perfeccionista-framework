package io.perfeccionista.framework.extension;

import io.perfeccionista.framework.AbstractParallelTestWithEnvironment;
import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.SetEnvironmentConfiguration;
import io.perfeccionista.framework.extension.configurations.TestEnvironmentParameterResolverConfiguration;
import io.perfeccionista.framework.extension.services.TestService1;
import io.perfeccionista.framework.extension.services.TestService2;
import io.perfeccionista.framework.value.ValueService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SetEnvironmentConfiguration(TestEnvironmentParameterResolverConfiguration.class)
public class PerfeccionistaExtension_ArgumentProviderTest extends AbstractParallelTestWithEnvironment {

    @BeforeEach
    void beforeEach(Environment environment,
                    TestService1 testService1,
                    TestService2 testService2,
                    ValueService valueService) {
        assertAll(
                () -> assertNotNull(environment, () -> "Provided environment argument is null"),
                () -> assertNotNull(testService1, () -> "Provided testService1 argument is null"),
                () -> assertNotNull(testService2, () -> "Provided testService2 argument is null"),
                () -> assertNotNull(valueService, () -> "Provided valueService argument is null")
        );
    }

    @Test
    void environmentArgumentProviderTest(Environment environment) {
        assertNotNull(environment, () -> "Provided environment argument is null");
    }

    @Test
    void serviceArgumentProviderTest(TestService1 testService) {
        assertNotNull(testService, () -> "Provided testService argument is null");
    }

    @Test
    void multipleServiceArgumentProviderTest(TestService2 testService2,
                                             TestService1 testService1,
                                             ValueService valueService) {
        assertAll(
                () -> assertNotNull(testService1, () -> "Provided testService1 argument is null"),
                () -> assertNotNull(testService2, () -> "Provided testService2 argument is null"),
                () -> assertNotNull(valueService, () -> "Provided valueService argument is null")
        );
    }


}
