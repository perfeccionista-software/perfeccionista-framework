package io.perfeccionista.framework.cucumber.parameters;

import io.perfeccionista.framework.measurements.Dimensions2D;
import org.jetbrains.annotations.NotNull;

public interface DimensionsParameter extends CucumberStepDefinitionParameter {

    @NotNull Dimensions2D getDimensions();

}
