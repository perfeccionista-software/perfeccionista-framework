package io.perfeccionista.framework.cucumber.parameters;

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
