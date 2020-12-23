package io.perfeccionista.framework.cucumber.parameters;

import io.perfeccionista.framework.comparators.string.StringValueComparator;
import io.perfeccionista.framework.comparators.SortDirection;
import org.jetbrains.annotations.NotNull;

public interface StringComparatorTypeParameter extends CucumberStepDefinitionParameter {

    @NotNull StringValueComparator findComparatorForDirection(SortDirection sortDirection);

}
