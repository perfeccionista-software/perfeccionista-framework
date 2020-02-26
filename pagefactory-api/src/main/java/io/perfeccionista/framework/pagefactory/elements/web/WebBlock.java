package io.perfeccionista.framework.pagefactory.elements.web;

import io.perfeccionista.framework.pagefactory.elements.Block;
import io.perfeccionista.framework.pagefactory.elements.registry.WebElementsRegistry;

public interface WebBlock extends Block, WebParentElement, WebChildElement {

    WebElementsRegistry getWebElementRegistry();

}
