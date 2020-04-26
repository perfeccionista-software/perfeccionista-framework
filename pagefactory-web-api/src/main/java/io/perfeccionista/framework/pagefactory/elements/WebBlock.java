package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.elements.registry.WebElementRegistry;

public interface WebBlock extends WebChildElement, WebParentElement {

    WebElementRegistry getElementRegistry();

}
