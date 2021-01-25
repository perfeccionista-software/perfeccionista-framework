package io.perfeccionista.framework.cucumber.parameters;

import io.perfeccionista.framework.measurements.Point2D;
import org.jetbrains.annotations.NotNull;

public interface LocationParameter extends CucumberStepDefinitionParameter {

    @NotNull Point2D getLocation();

}

