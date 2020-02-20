package io.perfeccionista.framework.datasource;

import org.junit.jupiter.api.Assertions;
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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(PerfeccionistaExtension.class)
@UseEnvironmentConfiguration(DataSourceLocalEnvironmentConfiguration.class)
public class DataSourceServiceTest {

    @Test
    void dataSourceServiceInitializationTest(Environment environment) {
        Optional<DataSourceService> optionalDataSourceService = environment.getService(DataSourceService.class);
        assertTrue(optionalDataSourceService.isPresent());
        DataSourceService dataSourceService = optionalDataSourceService.get();
        assertEquals(2, dataSourceService.stream().count());
    }

    @Test
    void dataSourceTest(Environment environment) {
        Optional<DataSourceService> optionalDataSourceService = environment.getService(DataSourceService.class);
        assertTrue(optionalDataSourceService.isPresent());
        DataSourceService dataSourceService = optionalDataSourceService.get();
        SimpleDataSource simpleDataSource = dataSourceService.get(SimpleDataSource.class);
        assertNotNull(simpleDataSource);
        User user = simpleDataSource.get("Jack");
        assertEquals(new User("Jack", "Black"), user);
        Professional professionalUser = simpleDataSource.get("Professional John", Professional.class);
        assertEquals(new Professional("Professional John", "White"), professionalUser);
        Assertions.assertEquals("User {name = Jack, surname = Black}", simpleDataSource.getString("Jack"));
    }

    @Test
    void dataConverterTest(Environment environment) {
        Optional<DataSourceService> optionalDataSourceService = environment.getService(DataSourceService.class);
        assertTrue(optionalDataSourceService.isPresent());
        DataSourceService dataSourceService = optionalDataSourceService.get();
        SimpleDataStorage simpleDataStorage = dataSourceService.get(SimpleDataStorage.class);
        assertNotNull(simpleDataStorage);
    }

}
