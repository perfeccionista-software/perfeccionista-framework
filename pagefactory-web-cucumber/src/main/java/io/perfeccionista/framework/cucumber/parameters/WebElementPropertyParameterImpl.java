package io.perfeccionista.framework.cucumber.parameters;

import org.jetbrains.annotations.NotNull;

public class WebElementPropertyParameterImpl implements WebElementPropertyParameter {

    private final String rawInput;

    public WebElementPropertyParameterImpl(String rawInput) {
        this.rawInput = rawInput;
    }

    @Override
    public @NotNull String getRaw() {
        return rawInput;
    }

}
