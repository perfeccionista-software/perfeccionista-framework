package io.perfeccionista.framework.cucumber.parameters;

import io.perfeccionista.framework.measurements.Location;
import org.jetbrains.annotations.NotNull;

public interface LocationParameter extends CucumberStepDefinitionParameter {

    @NotNull Location getLocation();

}

