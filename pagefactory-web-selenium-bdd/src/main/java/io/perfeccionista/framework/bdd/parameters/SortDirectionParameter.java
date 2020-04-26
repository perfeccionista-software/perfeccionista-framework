package io.perfeccionista.framework.bdd.parameters;

import io.perfeccionista.framework.bdd.comparators.SortDirection;

public interface SortDirectionParameter extends BddStepParameter {

    SortDirection getDirection();

}
