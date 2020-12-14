package io.perfeccionista.framework.cucumber.parameters;

import io.perfeccionista.framework.datasource.DataStorage;

public interface DataStorageParameter<K, V> extends CucumberStepParameter {

    DataStorage<K, V> get();

}
