package io.perfeccionista.framework.extension;

import io.perfeccionista.framework.AbstractParallelTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.EnvironmentConfiguration;
import io.perfeccionista.framework.UseEnvironment;
import io.perfeccionista.framework.extension.configurations.TestClassLocalEnvironmentConfiguration;
import io.perfeccionista.framework.extension.configurations.TestMethodLocalFirstEnvironmentConfiguration;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PerfeccionistaExtension_Test extends AbstractParallelTest {

    @Test
    void beforeEach_OnlyMethodConfiguration_Test() {
        ExtensionContext context = mock(ExtensionContext.class);
        when(context.getTestClass())
                .then(invocationOnMock -> Optional.of(TestWithoutConfiguration.class));
        when(context.getTestMethod())
                .then(invocationOnMock -> Optional.of(this.getClass().getDeclaredMethod("testMethodWithConfiguration")));

        PerfeccionistaExtension extension = new PerfeccionistaExtension();
        extension.beforeEach(context);
        Environment activeEnvironment = extension.activeEnvironment.get();

        assertAll(
                () -> assertNotNull(activeEnvironment),
                () -> {
                    Class<?> environmentConfigurationClass = extension.activeEnvironment.get().getEnvironmentConfiguration().getClass();
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

        PerfeccionistaExtension extension = new PerfeccionistaExtension();
        extension.beforeEach(context);
        Environment activeEnvironment = extension.activeEnvironment.get();

        assertAll(
                () -> assertNotNull(activeEnvironment),
                () -> {
                    Class<?> environmentConfigurationClass = extension.activeEnvironment.get().getEnvironmentConfiguration().getClass();
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

        PerfeccionistaExtension extension = new PerfeccionistaExtension();
        extension.beforeEach(context);
        Environment activeEnvironment = extension.activeEnvironment.get();

        assertAll(
                () -> assertNotNull(activeEnvironment),
                () -> {
                    Class<?> environmentConfigurationClass = extension.activeEnvironment.get().getEnvironmentConfiguration().getClass();
                    assertEquals(TestMethodLocalFirstEnvironmentConfiguration.class, environmentConfigurationClass);
                }
        );
    }

    @Test
    void findEnvironmentConfigurationForMethodTest() throws NoSuchMethodException {
        Optional<Class<? extends EnvironmentConfiguration>> environmentConfiguration = new PerfeccionistaExtension()
                .findEnvironmentConfiguration(this.getClass().getDeclaredMethod("testMethodWithConfiguration"));
        assertEquals(Optional.of(TestMethodLocalFirstEnvironmentConfiguration.class), environmentConfiguration);
    }

    @Test
    void findEnvironmentConfigurationForClassTest() {
        assertAll(
                () -> {
                    Optional<Class<? extends EnvironmentConfiguration>> environmentConfiguration = new PerfeccionistaExtension()
                            .findEnvironmentConfiguration(TestWithConfiguration.class);
                    assertEquals(Optional.of(TestClassLocalEnvironmentConfiguration.class), environmentConfiguration);
                },
                () -> {
                    Optional<Class<? extends EnvironmentConfiguration>> inheritedEnvironmentConfiguration = new PerfeccionistaExtension()
                            .findEnvironmentConfiguration(TestWithInheritedConfiguration.class);
                    assertEquals(Optional.of(TestClassLocalEnvironmentConfiguration.class), inheritedEnvironmentConfiguration);
                }
        );
    }

    private void testMethodWithoutConfiguration() {
        // do nothing
    }

    @UseEnvironment(TestMethodLocalFirstEnvironmentConfiguration.class)
    private void testMethodWithConfiguration() {}

    private static class TestWithoutConfiguration {}

    @UseEnvironment(TestClassLocalEnvironmentConfiguration.class)
    private static class TestWithConfiguration {}

    private static class TestWithInheritedConfiguration extends TestWithConfiguration {}

}
