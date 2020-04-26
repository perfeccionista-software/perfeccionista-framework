package io.perfeccionista.framework.bdd.parameters;

import io.perfeccionista.framework.bdd.comparators.SortDirection;

public class SortDirectionParameterImpl implements SortDirectionParameter {

    private final String rawInput;

    public SortDirectionParameterImpl(String rawInput) {
        this.rawInput = rawInput;
    }

    @Override
    public SortDirection getDirection() {
        return null;
    }

    @Override
    public String getRaw() {
        return null;
    }

}
