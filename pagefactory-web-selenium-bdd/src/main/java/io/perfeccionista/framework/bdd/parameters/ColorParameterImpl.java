package io.perfeccionista.framework.bdd.parameters;

import io.perfeccionista.framework.color.Color;

public class ColorParameterImpl implements ColorParameter {

    private final String rawInput;

    public ColorParameterImpl(String rawInput) {
        this.rawInput = rawInput;
    }

    @Override
    public Color getColor() {
        // TODO: Implement
        return null;
    }

    @Override
    public String getRaw() {
        return rawInput;
    }

}
