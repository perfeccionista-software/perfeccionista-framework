package io.perfeccionista.framework.cucumber.parameters;

import io.perfeccionista.framework.value.number.NumberValue;
import org.jetbrains.annotations.NotNull;

public interface ValueNumberParameter extends CucumberStepDefinitionParameter {

    @NotNull NumberValue<? extends Number> getValue();

}
