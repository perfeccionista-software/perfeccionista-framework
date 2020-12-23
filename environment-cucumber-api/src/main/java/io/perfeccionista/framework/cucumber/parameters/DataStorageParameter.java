package io.perfeccionista.framework.cucumber.parameters;

import io.perfeccionista.framework.datasource.DataStorage;
import org.jetbrains.annotations.NotNull;

public interface DataStorageParameter<K, V> extends CucumberStepDefinitionParameter {

    @NotNull DataStorage<K, V> get();

}
