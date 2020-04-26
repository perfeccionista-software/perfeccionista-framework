package io.perfeccionista.framework.bdd.parameters;

import io.perfeccionista.framework.value.string.StringValue;

public interface ValueStringParameter extends BddStepParameter {

    String getProcessedValue();

    StringValue getValue();

}
