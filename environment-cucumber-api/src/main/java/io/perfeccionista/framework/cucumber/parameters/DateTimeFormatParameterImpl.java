package io.perfeccionista.framework.cucumber.parameters;

import org.jetbrains.annotations.NotNull;

import java.time.format.DateTimeFormatter;

public class DateTimeFormatParameterImpl implements DateTimeFormatParameter {

    private final String rawInput;

    public DateTimeFormatParameterImpl(String rawInput) {
        this.rawInput = rawInput;
    }

    @Override
    public @NotNull DateTimeFormatter getFormatter() {
        return DateTimeFormatter.ofPattern(rawInput);
    }

    @Override
    public @NotNull String getRaw() {
        return rawInput;
    }

}
