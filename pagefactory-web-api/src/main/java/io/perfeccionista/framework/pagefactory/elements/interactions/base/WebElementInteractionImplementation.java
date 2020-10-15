package io.perfeccionista.framework.pagefactory.elements.interactions.base;

import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import org.jetbrains.annotations.NotNull;

public interface WebElementInteractionImplementation {

    WebChildElement execute(@NotNull WebChildElement sourceElement, @NotNull WebChildElement targetElement, Object... args);

}
