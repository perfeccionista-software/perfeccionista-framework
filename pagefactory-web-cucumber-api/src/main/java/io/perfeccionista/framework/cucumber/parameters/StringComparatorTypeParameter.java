package io.perfeccionista.framework.cucumber.parameters;

import io.perfeccionista.framework.comparators.string.StringValueComparator;
import io.perfeccionista.framework.comparators.SortDirection;

public interface StringComparatorTypeParameter extends CucumberStepParameter {

    StringValueComparator findComparatorForDirection(SortDirection sortDirection);

}
