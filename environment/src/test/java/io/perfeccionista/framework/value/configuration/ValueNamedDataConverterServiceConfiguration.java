package io.perfeccionista.framework.value.configuration;

import io.perfeccionista.framework.datasource.DataConverter;
import io.perfeccionista.framework.datasource.DataConverterServiceConfiguration;
import io.perfeccionista.framework.value.implementations.StringToUserDataConverter;
import io.perfeccionista.framework.value.implementations.UserToStringDataConverter;

import java.util.stream.Stream;

public class ValueNamedDataConverterServiceConfiguration implements DataConverterServiceConfiguration {

    @Override
    public Stream<DataConverter<?, ?>> getDataConverters() {
        return Stream.of(
                new StringToUserDataConverter(),
                new UserToStringDataConverter()
        );
    }

}
