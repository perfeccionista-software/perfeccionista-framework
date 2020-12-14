package io.perfeccionista.framework.cucumber.parameters;

import io.perfeccionista.framework.measurements.Location;

public interface LocationParameter extends CucumberStepParameter {

    Location getLocation();

}

