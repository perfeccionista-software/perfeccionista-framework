package io.perfeccionista.framework.bdd.parameters;

import io.perfeccionista.framework.measurements.Dimensions;

public class DimensionsParameterImpl implements DimensionsParameter {

    private final String rawInput;

    public DimensionsParameterImpl(String rawInput) {
        this.rawInput = rawInput;
    }

    @Override
    public Dimensions getDimensions() {
        // TODO: Implement
        return null;
    }

    @Override
    public String getRaw() {
        return rawInput;
    }

}
