package io.perfeccionista.framework.bdd.parameters;

import java.time.format.DateTimeFormatter;

public interface DateTimeFormatParameter extends BddStepParameter {

    DateTimeFormatter getFormatter();

}
