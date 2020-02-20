package io.perfeccionista.framework.datasource;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.UseEnvironmentConfiguration;
import io.perfeccionista.framework.datasource.configuration.DataConverterLocalEnvironmentConfiguration;
import io.perfeccionista.framework.extension.PerfeccionistaExtension;

@ExtendWith(PerfeccionistaExtension.class)
@UseEnvironmentConfiguration(DataConverterLocalEnvironmentConfiguration.class)
public class DataConverterServiceTest {

    // TODO: Implement
    @Test
    void dataConverterServiceInitializationTest(Environment environment) {

    }

}
