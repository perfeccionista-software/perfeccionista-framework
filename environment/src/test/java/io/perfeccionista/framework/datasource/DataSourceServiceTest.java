package io.perfeccionista.framework.datasource;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.UseEnvironmentConfiguration;
import io.perfeccionista.framework.datasource.configuration.DataSourceLocalEnvironmentConfiguration;
import io.perfeccionista.framework.datasource.implementations.SimpleDataSource;
import io.perfeccionista.framework.datasource.implementations.SimpleDataStorage;
import io.perfeccionista.framework.datasource.implementations.entities.Professional;
import io.perfeccionista.framework.datasource.implementations.entities.User;
import io.perfeccionista.framework.extension.PerfeccionistaExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(PerfeccionistaExtension.class)
@UseEnvironmentConfiguration(DataSourceLocalEnvironmentConfiguration.class)
public class DataSourceServiceTest {

    @Test
    void dataSourceServiceInitializationTest(Environment environment) {
        DataSourceService dataSourceService = environment.getService(DataSourceService.class);
        assertNotNull(dataSourceService);
        assertEquals(2, dataSourceService.stream().count());
    }

    @Test
    void dataSourceTest(Environment environment) {
        DataSourceService dataSourceService = environment.getService(DataSourceService.class);
        assertNotNull(dataSourceService);
        SimpleDataSource simpleDataSource = dataSourceService.get(SimpleDataSource.class);
        assertNotNull(simpleDataSource);
        User user = simpleDataSource.get("Jack");
        assertEquals(new User("Jack", "Black"), user);
        Professional professionalUser = simpleDataSource.get("Professional John", Professional.class);
        assertEquals(new Professional("Professional John", "White"), professionalUser);
        assertEquals("User {name = Jack, surname = Black}", simpleDataSource.getString("Jack"));
    }

    @Test
    void dataConverterTest(Environment environment) {
        DataSourceService dataSourceService = environment.getService(DataSourceService.class);
        assertNotNull(dataSourceService);
        SimpleDataStorage simpleDataStorage = dataSourceService.get(SimpleDataStorage.class);
        assertNotNull(simpleDataStorage);
    }

}
