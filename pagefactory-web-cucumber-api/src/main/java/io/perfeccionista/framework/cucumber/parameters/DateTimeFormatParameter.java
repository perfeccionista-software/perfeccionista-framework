package io.perfeccionista.framework.cucumber.parameters;

import java.time.format.DateTimeFormatter;

public interface DateTimeFormatParameter extends CucumberStepParameter {

    DateTimeFormatter getFormatter();

}
