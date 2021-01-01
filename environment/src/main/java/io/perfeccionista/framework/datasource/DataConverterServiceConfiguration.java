package io.perfeccionista.framework.datasource;

import io.perfeccionista.framework.service.ServiceConfiguration;

import java.util.Map;

public interface DataConverterServiceConfiguration extends ServiceConfiguration {

    Map<String, DataConverter<?, ?>> getDataConverters();

}
