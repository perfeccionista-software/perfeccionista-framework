package io.perfeccionista.framework;

import io.perfeccionista.framework.exceptions.RegisterDuplicate.RegisterDuplicateException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import io.perfeccionista.framework.service.Service;
import io.perfeccionista.framework.service.ServiceConfiguration;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

class EnvironmentTest extends AbstractParallelTest {

    @Test
    void initializationWithoutTestClassSuccessTest() {
        EnvironmentConfiguration configuration = new DefaultEnvironmentConfiguration();
        Environment environment = new Environment(configuration);
        assertEquals(configuration, environment.getEnvironmentConfiguration());
    }

    @Test
    void initializationFailedTest() {
        assertThrows(IllegalArgumentException.class, () -> new Environment(null));
    }

    @Test
    void notNullArgumentsTest() {
        Environment environment = new Environment(new DefaultEnvironmentConfiguration());
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> environment.register(null, mock(TestAdditionProvider2.class))),
                () -> assertThrows(IllegalArgumentException.class, () -> environment.register(TestAdditionProvider2.class, null)),
                () -> assertThrows(IllegalArgumentException.class, () -> environment.getService(null)),
                () -> assertThrows(IllegalArgumentException.class, () -> environment.checkEnvironmentConfiguration(null)),
                () -> assertThrows(IllegalArgumentException.class, () -> environment.initEnvironment(null)),
                () -> assertThrows(IllegalArgumentException.class, () ->
                        environment.initService(null, mock(ServiceConfiguration.class))),
                () -> assertThrows(IllegalArgumentException.class, () ->
                        environment.initService(TestAdditionProvider1.class, null))
        );
    }

    @Test
    void registerAdditionProviderSuccessTest() {
        Environment environment = new Environment(new DefaultEnvironmentConfiguration());
        TestAdditionProvider1 firstProvider = mock(TestAdditionProvider1.class);
        TestAdditionProvider2 secondProvider = mock(TestAdditionProvider2.class);
        environment.register(TestAdditionProvider1.class, firstProvider);
        environment.register(TestAdditionProvider2.class, secondProvider);
        assertAll(
                // проверяем получение провайдера по классу, по которому он зарегистрирован
                () -> assertEquals(firstProvider, environment.getService(TestAdditionProvider1.class)),
                () -> assertEquals(secondProvider, environment.getService(TestAdditionProvider2.class)),
                // проверяем наличие всех классов по которым зарегистрированы инстансы провайдеров
                () -> assertNotNull(environment.getServiceClasses()),
                () -> assertEquals(2, environment.getServiceClasses().size()),
                () -> assertTrue(environment.getServiceClasses().contains(TestAdditionProvider1.class)),
                () -> assertTrue(environment.getServiceClasses().contains(TestAdditionProvider2.class)),
                // проверяем наличие всех инстансов провайдеров получаемых через стрим
                () -> assertNotNull(environment.getServices()),
                () -> assertEquals(2L, environment.getServices().count())
        );
    }

    @Test
    void registerAdditionProviderFailedTest() {
        Environment environment = new Environment(new DefaultEnvironmentConfiguration());
        assertAll(
                // нельзя регистрировать провайдер по ключу null
                () -> assertThrows(IllegalArgumentException.class, () ->
                        environment.register(null, mock(TestAdditionProvider1.class))),
                // нельзя регистрировать null вместо экземпляра провайдера
                () -> assertThrows(IllegalArgumentException.class, () ->
                        environment.register(TestAdditionProvider1.class, null)),
                // экземпляр провайдера должен быть совместим с классом ключа по которому он регистрируется
                () -> assertThrows(ClassCastException.class, () ->
                        environment.register(TestAdditionProvider1.class, mock(Service.class))),
                () -> {
                    // нельзя зарегистрировать два экземпляра провайдера по одному и тому же ключу
                    environment.register(TestAdditionProvider1.class, mock(TestAdditionProvider1.class));
                    assertThrows(RegisterDuplicateException.class, () ->
                            environment.register(TestAdditionProvider1.class, mock(TestAdditionProvider1.class)));
                }
        );
    }

    @Test
    void threadLocalEnvironmentTest() {
        Environment environment = new Environment(new DefaultEnvironmentConfiguration());
        environment.setEnvironmentForCurrentThread();
        Assertions.assertEquals(environment, Environment.INSTANCES.get());
        Assertions.assertEquals(Optional.of(environment), Environment.get());
    }

    static abstract class TestAdditionProvider1 implements Service {}

    static abstract class TestAdditionProvider2 implements Service {}

}
