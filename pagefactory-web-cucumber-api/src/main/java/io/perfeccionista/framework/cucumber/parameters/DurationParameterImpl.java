package io.perfeccionista.framework.cucumber.parameters;

import org.jetbrains.annotations.NotNull;

import java.time.Duration;

public class DurationParameterImpl implements DurationParameter {

    private final String rawInput;

    public DurationParameterImpl(String rawInput) {
        this.rawInput = rawInput;
    }

    @Override
    public Duration getDuration() {
        return null;
    }

    @Override
    public @NotNull String getRaw() {
        return rawInput;
    }

}
