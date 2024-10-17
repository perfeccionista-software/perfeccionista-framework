package io.perfeccionista.framework.datasource.configuration;

import io.perfeccionista.framework.datasource.DataConverterHolder;
import io.perfeccionista.framework.datasource.DefaultDataConverterServiceConfiguration;
import io.perfeccionista.framework.datasource.implementations.SimpleDataConverter;
import io.perfeccionista.framework.value.implementations.StringToUserDataConverter;
import io.perfeccionista.framework.value.implementations.UserToStringDataConverter;

import java.util.stream.Stream;

public class TestDataConverterServiceConfiguration extends DefaultDataConverterServiceConfiguration {

    @Override
    public Stream<DataConverterHolder<?>> dataConverterHolders() {
        return Stream.of(
                DataConverterHolder.of(SimpleDataConverter.class),
                DataConverterHolder.of(StringToUserDataConverter.class),
                DataConverterHolder.of(UserToStringDataConverter.class)
        );
    }

}
