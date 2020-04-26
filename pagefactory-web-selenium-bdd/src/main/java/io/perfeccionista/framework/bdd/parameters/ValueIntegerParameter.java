package io.perfeccionista.framework.bdd.parameters;

import io.perfeccionista.framework.value.number.NumberValue;

// TODO: String, Number, Object ?
public interface ValueIntegerParameter extends BddStepParameter {

    int getProcessedValue();

    NumberValue<Integer> getValue();

}