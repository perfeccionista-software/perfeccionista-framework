package io.perfeccionista.framework;

import io.perfeccionista.framework.Environment.EnvironmentLogger;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import io.perfeccionista.framework.invocation.runner.InvocationRunnerConfiguration;
import io.perfeccionista.framework.invocation.timeouts.Timeouts;
import io.perfeccionista.framework.repeater.RepeatPolicy;
import io.perfeccionista.framework.service.Service;
import io.perfeccionista.framework.service.ServiceConfiguration;
import io.perfeccionista.framework.service.UseService;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

final class EnvironmentLoggerTest extends SimpleParallelTest {

    @Test
    void initializationSuccessTest() {
        EnvironmentLogger environmentLogger = EnvironmentLogger.of(new TestEnvironmentConfiguration());
        environmentLogger.addServiceRecord(TestEnvironmentConfiguration.class.getAnnotation(UseService.class));
        String environmentDescription = environmentLogger.toString();

        assertNotNull(environmentDescription);

        assertTrue(environmentDescription.contains("io.perfeccionista.framework.Environment"));
        assertTrue(environmentDescription.contains("io.perfeccionista.framework.action.runner.ActionRunnerConfiguration"));
        assertTrue(environmentDescription.contains("io.perfeccionista.framework.repeater.RepeatPolicy"));
        assertTrue(environmentDescription.contains("io.perfeccionista.framework.action.timeouts.Timeouts"));
     }

    @Test
    void initializationFailedTest() {
        assertThrows(IllegalArgumentException.class, () -> EnvironmentLogger.of(null));
    }

    @Test
    void notNullArgumentsTest() {
        EnvironmentLogger environmentLogger = EnvironmentLogger.of(new TestEnvironmentConfiguration());
        assertThrows(IllegalArgumentException.class, () -> environmentLogger.addServiceRecord(null));
    }

    @UseService(service = Service.class, configuration = ServiceConfiguration.class)
    static class TestEnvironmentConfiguration implements EnvironmentConfiguration {

        @Override
        public @NotNull InvocationRunnerConfiguration getActionRunnerConfiguration() {
            return mock(InvocationRunnerConfiguration.class);
        }

        @Override
        public @NotNull RepeatPolicy getRepeatPolicy() {
            return mock(RepeatPolicy.class);
        }

        @Override
        public @NotNull Timeouts getTimeouts() {
            return mock(Timeouts.class);
        }

    }

}
