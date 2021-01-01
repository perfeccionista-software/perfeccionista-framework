package io.perfeccionista.framework;

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
    void initializationFailedTest() {
        assertThrows(IllegalArgumentException.class, () -> EnvironmentLogger.of(null));
    }

    @Test
    void notNullArgumentsTest() {
        EnvironmentLogger environmentLogger = EnvironmentLogger.of(new DefaultEnvironmentConfiguration());
        assertThrows(IllegalArgumentException.class, () -> environmentLogger.addServiceRecord(null, 0, 0));
    }

}
