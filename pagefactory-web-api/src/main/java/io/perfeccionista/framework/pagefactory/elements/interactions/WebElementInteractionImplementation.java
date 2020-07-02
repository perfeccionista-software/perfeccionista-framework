package io.perfeccionista.framework.pagefactory.elements.interactions;

import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;

public interface WebElementInteractionImplementation {

    WebChildElement execute(WebChildElement sourceElement, WebChildElement targetElement, Object... args);

}
