package io.perfeccionista.framework.cucumber.parameters;

import io.perfeccionista.framework.value.number.NumberValue;

// TODO: String, Number, Object ?
public interface ValueIntegerParameter extends CucumberStepParameter {

    int getNotNullProcessedValue();

    Integer getProcessedValue();

    NumberValue<Integer> getValue();

}