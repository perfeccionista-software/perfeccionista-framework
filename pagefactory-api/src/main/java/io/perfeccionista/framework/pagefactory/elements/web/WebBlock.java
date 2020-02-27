package io.perfeccionista.framework.pagefactory.elements.web;

import io.perfeccionista.framework.pagefactory.elements.registry.WebElementRegistry;

public interface WebBlock extends WebChildElement, WebParentElement {

    WebElementRegistry getWebElementRegistry();

    int getIndex();

}
