package io.perfeccionista.framework.bdd.parameters;

public class NamedMethodParameterImpl implements NamedMethodParameter {

    private final String rawInput;

    public NamedMethodParameterImpl(String rawInput) {
        this.rawInput = rawInput;
    }

    @Override
    public String getRaw() {
        return rawInput;
    }

}
