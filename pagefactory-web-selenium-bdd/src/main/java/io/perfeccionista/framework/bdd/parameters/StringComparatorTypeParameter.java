package io.perfeccionista.framework.bdd.parameters;

import io.perfeccionista.framework.bdd.comparators.SortDirection;

import java.util.Comparator;

public interface StringComparatorTypeParameter extends BddStepParameter {

    Comparator<String> findForDirection(SortDirection sortDirection);

}
