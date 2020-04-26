package io.perfeccionista.framework.bdd.parameters;

public class WebElementInteractionParameterImpl implements WebElementInteractionParameter {

    private final String rawInput;

    public WebElementInteractionParameterImpl(String rawInput) {
        this.rawInput = rawInput;
    }

    @Override
    public String getRaw() {
        return rawInput;
    }

}
