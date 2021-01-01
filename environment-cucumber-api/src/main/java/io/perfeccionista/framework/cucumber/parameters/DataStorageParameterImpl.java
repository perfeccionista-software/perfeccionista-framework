package io.perfeccionista.framework.cucumber.parameters;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.datasource.DataSourceService;
import io.perfeccionista.framework.datasource.DataStorage;
import org.jetbrains.annotations.NotNull;

public class DataStorageParameterImpl<K, V> implements DataStorageParameter<K, V> {

    private final Environment environment;
    private final String rawInput;

    public DataStorageParameterImpl(Environment environment, String rawInput) {
        this.environment = environment;
        this.rawInput = rawInput;
    }

    @Override
    public @NotNull DataStorage<K, V> get() {
        return environment.getService(DataSourceService.class).get(rawInput);
    }

    @Override
    public @NotNull String getRaw() {
        return rawInput;
    }

}
