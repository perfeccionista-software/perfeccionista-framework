package io.perfeccionista.framework.datasource;

import java.util.stream.Stream;

public class DefaultDataConverterServiceConfiguration implements DataConverterServiceConfiguration {

    @Override
    public Stream<DataConverterHolder<?>> dataConverterHolders() {
        return Stream.of();
    }

}
