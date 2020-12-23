package io.perfeccionista.framework.cucumber.parameters;

import org.jetbrains.annotations.NotNull;

import java.time.Duration;

public interface DurationParameter extends CucumberStepDefinitionParameter {

    @NotNull Duration getDuration();

}
