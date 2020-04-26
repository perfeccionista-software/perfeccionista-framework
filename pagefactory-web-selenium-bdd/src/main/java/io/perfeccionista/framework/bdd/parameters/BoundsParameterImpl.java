package io.perfeccionista.framework.bdd.parameters;

import io.perfeccionista.framework.pagefactory.elements.methods.Bounds;

public class BoundsParameterImpl implements BoundsParameter {

    private final String rawInput;

    public BoundsParameterImpl(String rawInput) {
        this.rawInput = rawInput;
    }

    @Override
    public Bounds getBounds() {
        // TODO: Implement
        return null;
    }

    @Override
    public String getRaw() {
        return rawInput;
    }

}
