package io.perfeccionista.framework.bdd.parameters;

public class WebElementPropertyParameterImpl implements WebElementPropertyParameter {

    private final String rawInput;

    public WebElementPropertyParameterImpl(String rawInput) {
        this.rawInput = rawInput;
    }

    @Override
    public String getRaw() {
        return rawInput;
    }

}
