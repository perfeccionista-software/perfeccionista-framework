package io.perfeccionista.framework.extension;

import io.perfeccionista.framework.AbstractParallelTestWithEnvironment;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.EnvironmentConfiguration;
import io.perfeccionista.framework.UseEnvironment;
import io.perfeccionista.framework.extension.configurations.TestMethodLocalFirstEnvironmentConfiguration;
import io.perfeccionista.framework.extension.configurations.TestMethodLocalSecondEnvironmentConfiguration;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Execution(ExecutionMode.SAME_THREAD)
class PerfeccionistaExtension_WithoutTestClassEnvironment_Test extends AbstractParallelTestWithEnvironment {

    private static final Set<Environment> instances = new HashSet<>();
    private static final Map<Class<? extends EnvironmentConfiguration>, AtomicInteger> environmentConfigurationUsageCount = new HashMap<>();

    @BeforeAll
    static void setUp() {
        environmentConfigurationUsageCount.put(TestMethodLocalFirstEnvironmentConfiguration.class, new AtomicInteger(0));
        environmentConfigurationUsageCount.put(TestMethodLocalSecondEnvironmentConfiguration.class, new AtomicInteger(0));
    }

    @Test
    @UseEnvironment(TestMethodLocalFirstEnvironmentConfiguration.class)
    void testOneWithTestMethodEnvironment(Environment environment) {
        assertEquals(TestMethodLocalFirstEnvironmentConfiguration.class, environment.getEnvironmentConfiguration().getClass());
        calculate(environment);
    }

    @Test
    @UseEnvironment(TestMethodLocalSecondEnvironmentConfiguration.class)
    void testTwoWithTestMethodEnvironment(Environment environment) {
        assertEquals(TestMethodLocalSecondEnvironmentConfiguration.class, environment.getEnvironmentConfiguration().getClass());
        calculate(environment);
    }

    @Test
    @UseEnvironment(TestMethodLocalFirstEnvironmentConfiguration.class)
    void testThreeWithTestMethodEnvironment(Environment environment) {
        assertEquals(TestMethodLocalFirstEnvironmentConfiguration.class, environment.getEnvironmentConfiguration().getClass());
        calculate(environment);
    }

    @Test
    @UseEnvironment(TestMethodLocalSecondEnvironmentConfiguration.class)
    void testFourWithTestMethodEnvironment(Environment environment) {
        assertEquals(TestMethodLocalSecondEnvironmentConfiguration.class, environment.getEnvironmentConfiguration().getClass());
        calculate(environment);
    }

    @Test
    @UseEnvironment(TestMethodLocalFirstEnvironmentConfiguration.class)
    void testFiveWithTestMethodEnvironment(Environment environment) {
        assertEquals(TestMethodLocalFirstEnvironmentConfiguration.class, environment.getEnvironmentConfiguration().getClass());
        calculate(environment);
    }

    @Test
    void testOneWithoutEnvironment() {
        // empty test without Environment
    }

    @AfterAll
    static void tearDown() {
        assertAll(
                () -> assertEquals(3, environmentConfigurationUsageCount.get(TestMethodLocalFirstEnvironmentConfiguration.class).get()),
                () -> assertEquals(2, environmentConfigurationUsageCount.get(TestMethodLocalSecondEnvironmentConfiguration.class).get()),
                () -> assertEquals(5, instances.size())
        );
    }

    private static synchronized void calculate(Environment environment) {
        instances.add(environment);
        environmentConfigurationUsageCount.get(environment.getEnvironmentConfiguration().getClass()).incrementAndGet();
    }

}
