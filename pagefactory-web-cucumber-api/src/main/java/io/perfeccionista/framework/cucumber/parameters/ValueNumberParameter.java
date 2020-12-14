package io.perfeccionista.framework.cucumber.parameters;

import io.perfeccionista.framework.value.number.NumberValue;

public interface ValueNumberParameter extends CucumberStepParameter {

    Number getProcessedValue();

    NumberValue<Number> getValue();

}
