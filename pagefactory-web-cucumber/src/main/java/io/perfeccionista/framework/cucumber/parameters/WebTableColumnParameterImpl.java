package io.perfeccionista.framework.cucumber.parameters;

import org.jetbrains.annotations.NotNull;

public class WebTableColumnParameterImpl implements WebTableColumnParameter {

    private final String rawInput;

    public WebTableColumnParameterImpl(String rawInput) {
        this.rawInput = rawInput;
    }

    @Override
    public @NotNull String getRaw() {
        return rawInput;
    }

}
