package io.perfeccionista.framework.datasource;

import io.perfeccionista.framework.AbstractParallelTestWithEnvironment;
import io.perfeccionista.framework.datasource.implementations.SimpleDataConverter;
import io.perfeccionista.framework.datasource.entities.User;
import io.perfeccionista.framework.value.implementations.StringToUserDataConverter;
import io.perfeccionista.framework.value.implementations.UserToStringDataConverter;
import org.junit.jupiter.api.Test;
import io.perfeccionista.framework.Environment;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DataConverterServiceTest extends AbstractParallelTestWithEnvironment {

    @Test
    void dataConverterServiceInitializationTest(Environment environment) {
        var dataConverterService = environment.getService(DataConverterService.class);
        assertAll(
                () -> assertNotNull(dataConverterService),
                () -> assertEquals(3, dataConverterService.stream().count())
        );
    }

    @Test
    void dataConverterTest(Environment environment) {
        var dataConverterService = environment.getService(DataConverterService.class);
        assertAll(
                () -> assertNotNull(dataConverterService),
                () -> assertNotNull(dataConverterService.get(SimpleDataConverter.class)),
                () -> assertNotNull(dataConverterService.get(StringToUserDataConverter.class)),
                () -> assertNotNull(dataConverterService.get(UserToStringDataConverter.class))
        );
    }

    @Test
    void dataConverterProcessingTest(Environment environment) {
        var dataConverterService = environment.getService(DataConverterService.class);
        assertAll(
                () -> assertNotNull(dataConverterService),
                () -> {
                    var stringToUserDataConverter = dataConverterService.get(StringToUserDataConverter.class);
                    var userToStringDataConverter = dataConverterService.get(UserToStringDataConverter.class);
                    assertAll(
                            () -> assertNotNull(stringToUserDataConverter),
                            () -> {
                                var user = stringToUserDataConverter.convert("Jack Black", null);
                                assertEquals(new User("Jack", "Black"), user);
                            },
                            () -> assertNotNull(userToStringDataConverter),
                            () -> {
                                var userName = userToStringDataConverter.convert(new User("Jack", "Black"), null);
                                assertEquals("Jack Black", userName);
                            }
                    );
                }
        );
    }

}
