package io.perfeccionista.framework.cucumber.parameters;

import io.perfeccionista.framework.color.Color;
import org.jetbrains.annotations.NotNull;

public interface ColorParameter extends CucumberStepDefinitionParameter {

    @NotNull Color getColor();

}
