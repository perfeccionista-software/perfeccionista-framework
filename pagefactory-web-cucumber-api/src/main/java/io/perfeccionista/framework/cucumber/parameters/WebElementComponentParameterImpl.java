package io.perfeccionista.framework.cucumber.parameters;

public class WebElementComponentParameterImpl implements WebElementComponentParameter {

    private final String rawInput;

    public WebElementComponentParameterImpl(String rawInput) {
        this.rawInput = rawInput;
    }

    @Override
    public String getRaw() {
        return rawInput;
    }

}
