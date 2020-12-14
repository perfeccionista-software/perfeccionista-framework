package io.perfeccionista.framework.cucumber.parameters;

import io.perfeccionista.framework.pagefactory.elements.base.WebChildElementBase;
import io.perfeccionista.framework.pagefactory.elements.base.WebParentElement;
import org.jetbrains.annotations.NotNull;

public interface WebElementParameter<T extends WebChildElementBase> extends CucumberStepParameter {

    @NotNull T getElement(@NotNull WebParentElement parentContext, @NotNull Class<T> elementType);

}
