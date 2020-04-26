package io.perfeccionista.framework.bdd.parameters;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.datasource.DataStorage;
import io.perfeccionista.framework.datasource.NamedDataSourceService;

public class DataStorageParameterImpl<K, V> implements DataStorageParameter<K, V> {

    private final Environment environment;
    private final String rawInput;

    public DataStorageParameterImpl(Environment environment, String rawInput) {
        this.environment = environment;
        this.rawInput = rawInput;
    }

    @Override
    public DataStorage<K, V> get() {
        return environment.getService(NamedDataSourceService.class).get(rawInput);
    }

    @Override
    public String getRaw() {
        return rawInput;
    }

}
