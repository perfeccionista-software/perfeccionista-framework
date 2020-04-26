package io.perfeccionista.framework.extension;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.EnvironmentConfiguration;
import io.perfeccionista.framework.UseEnvironmentConfiguration;
import io.perfeccionista.framework.extension.configurations.TestClassLocalEnvironmentConfiguration;
import io.perfeccionista.framework.extension.configurations.TestMethodLocalFirstEnvironmentConfiguration;
import io.perfeccionista.framework.extension.configurations.TestMethodLocalSecondEnvironmentConfiguration;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(Lifecycle.PER_METHOD)
@Execution(ExecutionMode.CONCURRENT)
@ExtendWith(PerfeccionistaExtension.class)
@UseEnvironmentConfiguration(TestClassLocalEnvironmentConfiguration.class)
final class PerfeccionistaExtension_LocalEnvironment_PerMethodLifecycle_ConcurrentTest {

    private static Map<Long, Set<Environment>> instances = new HashMap<>();
    private static Map<Class<? extends EnvironmentConfiguration>, AtomicInteger> environmentConfigurationUsageCount = new HashMap<>();

    @BeforeAll
    static void setUp() {
        environmentConfigurationUsageCount.put(TestClassLocalEnvironmentConfiguration.class, new AtomicInteger(0));
        environmentConfigurationUsageCount.put(TestMethodLocalFirstEnvironmentConfiguration.class, new AtomicInteger(0));
        environmentConfigurationUsageCount.put(TestMethodLocalSecondEnvironmentConfiguration.class, new AtomicInteger(0));
    }

    @Test
    void testOne(Environment environment) {
        assertEquals(TestClassLocalEnvironmentConfiguration.class, environment.getEnvironmentConfiguration().getClass());
        calculate(environment);
    }

    @Test
    void testTwo(Environment environment) {
        assertEquals(TestClassLocalEnvironmentConfiguration.class, environment.getEnvironmentConfiguration().getClass());
        calculate(environment);
    }

    @Test
    @UseEnvironmentConfiguration(TestMethodLocalFirstEnvironmentConfiguration.class)
    void testThree(Environment environment) {
        assertEquals(TestMethodLocalFirstEnvironmentConfiguration.class, environment.getEnvironmentConfiguration().getClass());
        calculate(environment);
    }

    @Test
    void testFour(Environment environment) {
        assertEquals(TestClassLocalEnvironmentConfiguration.class, environment.getEnvironmentConfiguration().getClass());
        calculate(environment);
    }

    @Test
    @UseEnvironmentConfiguration(TestMethodLocalSecondEnvironmentConfiguration.class)
    void testFive(Environment environment) {
        assertEquals(TestMethodLocalSecondEnvironmentConfiguration.class, environment.getEnvironmentConfiguration().getClass());
        calculate(environment);
    }

    @Test
    void testSix(Environment environment) {
        assertEquals(TestClassLocalEnvironmentConfiguration.class, environment.getEnvironmentConfiguration().getClass());
        calculate(environment);
    }

    @Test
    @UseEnvironmentConfiguration(TestMethodLocalFirstEnvironmentConfiguration.class)
    void testSeven(Environment environment) {
        assertEquals(TestMethodLocalFirstEnvironmentConfiguration.class, environment.getEnvironmentConfiguration().getClass());
        calculate(environment);
    }

    @Test
    @UseEnvironmentConfiguration(TestMethodLocalSecondEnvironmentConfiguration.class)
    void testEight(Environment environment) {
        assertEquals(TestMethodLocalSecondEnvironmentConfiguration.class, environment.getEnvironmentConfiguration().getClass());
        calculate(environment);
    }

    @Test
    void testNine(Environment environment) {
        assertEquals(TestClassLocalEnvironmentConfiguration.class, environment.getEnvironmentConfiguration().getClass());
        calculate(environment);
    }

    @Test
    void testTen(Environment environment) {
        assertEquals(TestClassLocalEnvironmentConfiguration.class, environment.getEnvironmentConfiguration().getClass());
        calculate(environment);
    }

    @AfterAll
    static void tearDown() {
        assertEquals(6, environmentConfigurationUsageCount.get(TestClassLocalEnvironmentConfiguration.class).get());
        assertEquals(2, environmentConfigurationUsageCount.get(TestMethodLocalFirstEnvironmentConfiguration.class).get());
        assertEquals(2, environmentConfigurationUsageCount.get(TestMethodLocalSecondEnvironmentConfiguration.class).get());
        assertEquals(10, instances.values().stream().map(Set::size).reduce(0, Integer::sum));
    }

    private static synchronized void calculate(Environment environment) {
        Long threadId = Thread.currentThread().getId();
        Set<Environment> threadInstances = instances.computeIfAbsent(threadId, k -> new HashSet<>());
        threadInstances.add(environment);
        environmentConfigurationUsageCount.get(environment.getEnvironmentConfiguration().getClass()).incrementAndGet();
    }

}
