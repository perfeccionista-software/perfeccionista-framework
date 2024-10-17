package io.perfeccionista.framework.datasource;

import io.perfeccionista.framework.service.ServiceConfiguration;

import java.util.stream.Stream;

public interface DataConverterServiceConfiguration extends ServiceConfiguration {

    Stream<DataConverterHolder<?>> dataConverterHolders();

}
