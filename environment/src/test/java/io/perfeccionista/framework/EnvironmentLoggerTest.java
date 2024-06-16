package io.perfeccionista.framework;

import io.perfeccionista.framework.exceptions.impl.PreconditionViolationException;
import io.perfeccionista.framework.utils.EnvironmentLogger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EnvironmentLoggerTest extends AbstractParallelTest {

    @Test
    void initializationSuccessTest() {
        EnvironmentLogger environmentLogger = EnvironmentLogger.of(new DefaultEnvironmentConfiguration());
        String environmentDescription = environmentLogger.toString();
        assertAll(
                () -> assertNotNull(environmentDescription),
                () -> assertTrue(environmentDescription.contains("io.perfeccionista.framework.Environment"))
        );
     }

    @Test
    void initializationWithTestNameSuccessTest() {
        EnvironmentLogger environmentLogger = EnvironmentLogger.of(
                new DefaultEnvironmentConfiguration(),
                this.getClass().getCanonicalName() + "#initializationWithTestNameSuccessTest"
        );
        String environmentDescription = environmentLogger.toString();
        assertAll(
                () -> assertNotNull(environmentDescription),
                () -> assertTrue(environmentDescription.contains("io.perfeccionista.framework.Environment")),
                () -> assertTrue(environmentDescription.contains("Environment for test [io.perfeccionista.framework.EnvironmentLoggerTest#initializationWithTestNameSuccessTest]"))
        );
    }

    @Test
    void initializationFailedTest() {
        assertThrows(PreconditionViolationException.class, () -> EnvironmentLogger.of(null));
    }

    @Test
    void notNullArgumentsTest() {
        EnvironmentLogger environmentLogger = EnvironmentLogger.of(new DefaultEnvironmentConfiguration());
        assertThrows(PreconditionViolationException.class, () -> environmentLogger.addServiceRecord(null, 0, 0));
    }

}
