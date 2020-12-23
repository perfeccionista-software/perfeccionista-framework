package io.perfeccionista.framework.cucumber.parameters;

import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import org.jetbrains.annotations.NotNull;

public class WebBlockElementParameterImpl<T extends WebChildElement> implements WebBlockElementParameter<T> {

    private final String rawInput;

    public WebBlockElementParameterImpl(String rawInput) {
        this.rawInput = rawInput;
    }

    @Override
    public @NotNull String getRaw() {
        return rawInput;
    }

}
