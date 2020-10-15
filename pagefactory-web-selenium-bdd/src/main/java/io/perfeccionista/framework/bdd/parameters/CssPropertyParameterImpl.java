package io.perfeccionista.framework.bdd.parameters;

public class CssPropertyParameterImpl implements CssPropertyParameter {

    private final String rawInput;

    public CssPropertyParameterImpl(String rawInput) {
        this.rawInput = rawInput;
    }

    @Override
    public String getRaw() {
        return rawInput;
    }

}
