package io.perfeccionista.framework;

import org.awaitility.Awaitility;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class EnvironmentInheritedByChildThreadTest extends AbstractParallelTestWithEnvironment {

    @Test
    void environmentInheritedInChildThreadTest(Environment environment) {
        Awaitility.await()
                .atMost(5, TimeUnit.SECONDS)
                .pollInterval(1, TimeUnit.SECONDS)
                .until(() -> {
                    Environment childThreadEnvironment = Environment.getForCurrentThread();
                    Assertions.assertNotNull(childThreadEnvironment);
                    Assertions.assertEquals(environment, childThreadEnvironment);
                    return Objects.nonNull(Environment.getForCurrentThread());
                });
    }

}
