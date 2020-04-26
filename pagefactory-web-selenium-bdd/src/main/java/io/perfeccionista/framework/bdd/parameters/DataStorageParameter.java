package io.perfeccionista.framework.bdd.parameters;

import io.perfeccionista.framework.datasource.DataStorage;

public interface DataStorageParameter<K, V> extends BddStepParameter {

    DataStorage<K, V> get();

}
