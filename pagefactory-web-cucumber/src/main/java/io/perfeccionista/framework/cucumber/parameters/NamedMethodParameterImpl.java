package io.perfeccionista.framework.cucumber.parameters;

import org.jetbrains.annotations.NotNull;

public class NamedMethodParameterImpl implements NamedMethodParameter {

    private final String rawInput;

    public NamedMethodParameterImpl(String rawInput) {
        this.rawInput = rawInput;
    }

    @Override
    public @NotNull String getRaw() {
        return rawInput;
    }

}
