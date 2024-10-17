package io.perfeccionista.framework.value.configurations;

import io.perfeccionista.framework.datasource.DataConverterHolder;
import io.perfeccionista.framework.datasource.DefaultDataConverterServiceConfiguration;
import io.perfeccionista.framework.value.implementations.StringToUserDataConverter;
import io.perfeccionista.framework.value.implementations.UserToStringDataConverter;

import java.util.stream.Stream;

public class ValueDataConverterServiceConfiguration extends DefaultDataConverterServiceConfiguration {

    @Override
    public Stream<DataConverterHolder<?>> dataConverterHolders() {
        return Stream.of(
                DataConverterHolder.of(StringToUserDataConverter.class),
                DataConverterHolder.of(UserToStringDataConverter.class)
        );
    }

}
