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
        DataConverterService dataConverterService = environment.getService(DataConverterService.class);
        assertAll(
                () -> assertNotNull(dataConverterService),
                () -> assertEquals(3, dataConverterService.stream().count())
        );
    }

    @Test
    void dataConverterTest(Environment environment) {
        DataConverterService dataConverterService = environment.getService(DataConverterService.class);
        assertAll(
                () -> assertNotNull(dataConverterService),
                () -> assertNotNull(dataConverterService.get(SimpleDataConverter.class)),
                () -> assertNotNull(dataConverterService.get(StringToUserDataConverter.class)),
                () -> assertNotNull(dataConverterService.get(UserToStringDataConverter.class))
        );
    }

    @Test
    void dataConverterProcessingTest(Environment environment) {
        DataConverterService dataConverterService = environment.getService(DataConverterService.class);
        assertAll(
                () -> assertNotNull(dataConverterService),
                () -> {
                    StringToUserDataConverter stringToUserDataConverter = dataConverterService.get(StringToUserDataConverter.class);
                    UserToStringDataConverter userToStringDataConverter = dataConverterService.get(UserToStringDataConverter.class);
                    assertAll(
                            () -> assertNotNull(stringToUserDataConverter),
                            () -> {
                                User user = stringToUserDataConverter.convert("Jack Black", null);
                                assertEquals(new User("Jack", "Black"), user);
                            },
                            () -> assertNotNull(userToStringDataConverter),
                            () -> {
                                String userName = userToStringDataConverter.convert(new User("Jack", "Black"), null);
                                assertEquals("Jack Black", userName);
                            }
                    );
                }
        );
    }

}
