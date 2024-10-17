package io.perfeccionista.framework.extension;

import io.perfeccionista.framework.AbstractParallelTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtensionContext;
import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.EnvironmentConfiguration;
import io.perfeccionista.framework.SetEnvironmentConfiguration;
import io.perfeccionista.framework.extension.configurations.TestClassLocalEnvironmentConfiguration;
import io.perfeccionista.framework.extension.configurations.TestMethodLocalFirstEnvironmentConfiguration;

import java.util.Optional;

import static io.perfeccionista.framework.utils.EnvironmentConfigurationResolver.resolveEnvironmentConfiguration;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PerfeccionistaExtension_Test extends AbstractParallelTest {

    @Test
    void beforeEach_OnlyMethodConfiguration_Test(TestInfo testInfo) {
        ExtensionContext context = mock(ExtensionContext.class);
        when(context.getTestClass())
                .then(invocationOnMock -> Optional.of(TestWithoutConfiguration.class));
        when(context.getTestMethod())
                .then(invocationOnMock -> Optional.of(this.getClass().getDeclaredMethod("testMethodWithConfiguration")));
        when(context.getUniqueId())
                .then(invocationOnMock -> "007");

        PerfeccionistaExtension extension = new PerfeccionistaExtension();
        extension.beforeEach(context);
        Environment activeEnvironment = extension.testCaseEnvironment.get(context.getUniqueId());

        assertAll(
                () -> assertNotNull(activeEnvironment),
                () -> {
                    Class<?> environmentConfigurationClass = extension.testCaseEnvironment
                            .get("007")
                            .getEnvironmentConfiguration()
                            .getClass();
                    assertEquals(TestMethodLocalFirstEnvironmentConfiguration.class, environmentConfigurationClass);
                }
        );
    }

    @Test
    void beforeEach_OnlyTestConfiguration_Test() {
        ExtensionContext context = mock(ExtensionContext.class);
        when(context.getTestClass())
                .then(invocationOnMock -> Optional.of(TestWithConfiguration.class));
        when(context.getTestMethod())
                .then(invocationOnMock -> Optional.of(this.getClass().getDeclaredMethod("testMethodWithoutConfiguration")));
        when(context.getUniqueId())
                .then(invocationOnMock -> "007");

        PerfeccionistaExtension extension = new PerfeccionistaExtension();
        extension.beforeEach(context);
        Environment activeEnvironment = extension.testCaseEnvironment.get(context.getUniqueId());

        assertAll(
                () -> assertNotNull(activeEnvironment),
                () -> {
                    Class<?> environmentConfigurationClass = extension.testCaseEnvironment
                            .get("007")
                            .getEnvironmentConfiguration()
                            .getClass();
                    assertEquals(TestClassLocalEnvironmentConfiguration.class, environmentConfigurationClass);
                }
        );
    }

    @Test
    void beforeEach_TestAndMethodConfiguration_Test() {
        ExtensionContext context = mock(ExtensionContext.class);
        when(context.getTestClass())
                .then(invocationOnMock -> Optional.of(TestWithConfiguration.class));
        when(context.getTestMethod())
                .then(invocationOnMock -> Optional.of(this.getClass().getDeclaredMethod("testMethodWithConfiguration")));
        when(context.getUniqueId())
                .then(invocationOnMock -> "007");

        PerfeccionistaExtension extension = new PerfeccionistaExtension();
        extension.beforeEach(context);
        Environment activeEnvironment = extension.testCaseEnvironment.get(context.getUniqueId());

        assertAll(
                () -> assertNotNull(activeEnvironment),
                () -> {
                    Class<?> environmentConfigurationClass = extension.testCaseEnvironment
                            .get("007")
                            .getEnvironmentConfiguration()
                            .getClass();
                    assertEquals(TestMethodLocalFirstEnvironmentConfiguration.class, environmentConfigurationClass);
                }
        );
    }

    @Test
    void findEnvironmentConfigurationForMethodTest() throws NoSuchMethodException {
        EnvironmentConfiguration environmentConfiguration = resolveEnvironmentConfiguration(
                this.getClass().getDeclaredMethod("testMethodWithConfiguration"),
                this.getClass()
        );
        assertEquals(TestMethodLocalFirstEnvironmentConfiguration.class, environmentConfiguration.getClass());
    }

    @Test
    void findEnvironmentConfigurationForClassTest() {
        assertAll(
                () -> {
                    EnvironmentConfiguration environmentConfiguration = resolveEnvironmentConfiguration(
                            this.getClass().getDeclaredMethod("testMethodWithoutConfiguration"),
                            TestWithConfiguration.class
                    );
                    assertEquals(TestClassLocalEnvironmentConfiguration.class, environmentConfiguration.getClass());
                },
                () -> {
                    EnvironmentConfiguration inheritedEnvironmentConfiguration = resolveEnvironmentConfiguration(
                            this.getClass().getDeclaredMethod("testMethodWithoutConfiguration"),
                            TestWithInheritedConfiguration.class
                    );
                    assertEquals(TestClassLocalEnvironmentConfiguration.class, inheritedEnvironmentConfiguration.getClass());
                }
        );
    }

    private void testMethodWithoutConfiguration() {
        // do nothing
    }

    @SetEnvironmentConfiguration(TestMethodLocalFirstEnvironmentConfiguration.class)
    private void testMethodWithConfiguration() {}

    private static class TestWithoutConfiguration {}

    @SetEnvironmentConfiguration(TestClassLocalEnvironmentConfiguration.class)
    private static class TestWithConfiguration {}

    private static class TestWithInheritedConfiguration extends TestWithConfiguration {}

}
