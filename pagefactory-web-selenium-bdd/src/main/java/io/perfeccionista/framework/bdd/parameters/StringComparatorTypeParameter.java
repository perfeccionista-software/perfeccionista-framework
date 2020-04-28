package io.perfeccionista.framework.bdd.parameters;

import io.perfeccionista.framework.bdd.comparators.BddValueComparator;
import io.perfeccionista.framework.bdd.comparators.SortDirection;

public interface StringComparatorTypeParameter extends BddStepParameter {

    BddValueComparator findComparatorForDirection(SortDirection sortDirection);

}
