package io.perfeccionista.framework.cucumber.parameters;

import io.perfeccionista.framework.comparators.SortDirection;
import org.jetbrains.annotations.NotNull;

public interface SortDirectionParameter extends CucumberStepDefinitionParameter {

    @NotNull SortDirection getDirection();

}
