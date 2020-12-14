package io.perfeccionista.framework.cucumber.parameters;

import io.perfeccionista.framework.comparators.SortDirection;

public interface SortDirectionParameter extends CucumberStepParameter {

    SortDirection getDirection();

}
