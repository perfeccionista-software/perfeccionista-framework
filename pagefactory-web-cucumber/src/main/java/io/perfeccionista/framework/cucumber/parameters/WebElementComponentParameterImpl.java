package io.perfeccionista.framework.cucumber.parameters;

import org.jetbrains.annotations.NotNull;

public class WebElementComponentParameterImpl implements WebElementComponentParameter {

    private final String rawInput;

    public WebElementComponentParameterImpl(String rawInput) {
        this.rawInput = rawInput;
    }

    @Override
    public @NotNull String getRaw() {
        return rawInput;
    }

}
