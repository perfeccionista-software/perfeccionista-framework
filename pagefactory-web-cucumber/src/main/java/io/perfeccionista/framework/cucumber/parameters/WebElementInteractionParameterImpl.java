package io.perfeccionista.framework.cucumber.parameters;

import org.jetbrains.annotations.NotNull;

public class WebElementInteractionParameterImpl implements WebElementInteractionParameter {

    private final String rawInput;

    public WebElementInteractionParameterImpl(String rawInput) {
        this.rawInput = rawInput;
    }

    @Override
    public @NotNull String getRaw() {
        return rawInput;
    }

}
