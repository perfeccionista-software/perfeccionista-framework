package io.perfeccionista.framework.datasource.configuration;

import io.perfeccionista.framework.datasource.DataConverter;
import io.perfeccionista.framework.datasource.DataConverterServiceConfiguration;
import io.perfeccionista.framework.datasource.implementations.SimpleDataConverter;

import java.util.stream.Stream;

public class TestDataConverterServiceConfiguration implements DataConverterServiceConfiguration {

    @Override
    public Stream<DataConverter<?, ?>> getDataConverters() {
        return Stream.of(
                new SimpleDataConverter()
        );
    }

}
