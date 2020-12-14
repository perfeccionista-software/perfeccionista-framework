package io.perfeccionista.framework.cucumber.parameters;

import io.perfeccionista.framework.measurements.Dimensions;

public interface DimensionsParameter extends CucumberStepParameter {

    Dimensions getDimensions();

}
