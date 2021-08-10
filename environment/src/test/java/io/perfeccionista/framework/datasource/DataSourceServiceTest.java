package io.perfeccionista.framework.datasource;

import io.perfeccionista.framework.AbstractParallelTestWithEnvironment;
import io.perfeccionista.framework.value.implementations.StringDataSource;
import io.perfeccionista.framework.value.implementations.UserDataSource;
import org.junit.jupiter.api.Test;
import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.datasource.implementations.SimpleDataSource;
import io.perfeccionista.framework.datasource.implementations.SimpleDataStorage;
import io.perfeccionista.framework.datasource.entities.Professional;
import io.perfeccionista.framework.datasource.entities.User;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DataSourceServiceTest extends AbstractParallelTestWithEnvironment {

    @Test
    void dataSourceServiceInitializationTest(Environment environment) {
        DataSourceService dataSourceService = environment.getService(DataSourceService.class);
        assertAll(
                () -> assertNotNull(dataSourceService),
                () -> assertEquals(6, dataSourceService.stream().count())
        );
    }

    @Test
    void dataSourceTest(Environment environment) {
        DataSourceService dataSourceService = environment.getService(DataSourceService.class);
        assertAll(
                () -> assertNotNull(dataSourceService),
                () -> assertNotNull(dataSourceService.get(StashDataSource.class)),
                () -> assertNotNull(dataSourceService.get(SimpleDataSource.class)),
                () -> assertNotNull(dataSourceService.get(SimpleDataStorage.class)),
                () -> assertNotNull(dataSourceService.get(StringDataSource.class)),
                () -> assertNotNull(dataSourceService.get(UserDataSource.class))
        );
    }

    @Test
    void dataSourceProcessingTest(Environment environment) {
        DataSourceService dataSourceService = environment.getService(DataSourceService.class);
        assertAll(
                () -> assertNotNull(dataSourceService),
                () -> {
                    SimpleDataSource simpleDataSource = dataSourceService.get(SimpleDataSource.class);
                    assertAll(
                            () -> assertNotNull(simpleDataSource),
                            () -> {
                                User user = simpleDataSource.get("Jack");
                                assertEquals(new User("Jack", "Black"), user);
                            },
                            () -> {
                                Professional professionalUser = simpleDataSource.get("Professional John", Professional.class);
                                assertEquals(new Professional("Professional John", "White"), professionalUser);
                                assertEquals("Jack Black", simpleDataSource.getString("Jack"));
                            }
                    );
                }
        );
    }

}
