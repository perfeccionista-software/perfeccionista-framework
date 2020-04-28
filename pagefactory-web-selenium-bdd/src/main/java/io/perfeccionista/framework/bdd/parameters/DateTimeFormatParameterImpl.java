package io.perfeccionista.framework.bdd.parameters;

import java.time.format.DateTimeFormatter;

public class DateTimeFormatParameterImpl implements DateTimeFormatParameter {

    private final String rawInput;

    public DateTimeFormatParameterImpl(String rawInput) {
        this.rawInput = rawInput;
    }

    @Override
    public DateTimeFormatter getFormatter() {
        return DateTimeFormatter.ofPattern(rawInput);
    }

    @Override
    public String getRaw() {
        return rawInput;
    }

}
