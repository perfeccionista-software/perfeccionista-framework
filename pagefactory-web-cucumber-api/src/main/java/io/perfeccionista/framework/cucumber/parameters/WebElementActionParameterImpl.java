package io.perfeccionista.framework.cucumber.parameters;

public class WebElementActionParameterImpl implements WebElementActionParameter {

    private final String rawInput;

    public WebElementActionParameterImpl(String rawInput) {
        this.rawInput = rawInput;
    }

    @Override
    public String getRaw() {
        return rawInput;
    }

}
