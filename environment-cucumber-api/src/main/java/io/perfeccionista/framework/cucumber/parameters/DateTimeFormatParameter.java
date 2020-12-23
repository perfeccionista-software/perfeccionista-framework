package io.perfeccionista.framework.cucumber.parameters;

import org.jetbrains.annotations.NotNull;

import java.time.format.DateTimeFormatter;

public interface DateTimeFormatParameter extends CucumberStepDefinitionParameter {

    @NotNull DateTimeFormatter getFormatter();

}
