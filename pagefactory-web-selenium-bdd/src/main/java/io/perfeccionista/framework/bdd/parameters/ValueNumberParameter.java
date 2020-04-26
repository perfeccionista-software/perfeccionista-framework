package io.perfeccionista.framework.bdd.parameters;

import io.perfeccionista.framework.value.number.NumberValue;

public interface ValueNumberParameter extends BddStepParameter {

    Number getProcessedValue();

    NumberValue<Number> getValue();

}
