package io.perfeccionista.framework.bdd.parameters;

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
    public String getRaw() {
        return rawInput;
    }

}
