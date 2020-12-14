package io.perfeccionista.framework.cucumber.parameters;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.base.WebParentElement;
import org.jetbrains.annotations.NotNull;

public class WebElementParameterImpl<T extends WebChildElement> implements WebElementParameter<T> {

    private final Environment environment;
    private final String rawInput;

    public WebElementParameterImpl(Environment environment, String rawInput) {
        this.environment = environment;
        this.rawInput = rawInput;
    }

    @Override
    public @NotNull T getElement(@NotNull WebParentElement parentContext, @NotNull Class<T> elementType) {
        return parentContext.getElementRegistry().getRequiredElementByPath(rawInput, elementType);
    }

    @Override
    public @NotNull String getRaw() {
        return rawInput;
    }

}
