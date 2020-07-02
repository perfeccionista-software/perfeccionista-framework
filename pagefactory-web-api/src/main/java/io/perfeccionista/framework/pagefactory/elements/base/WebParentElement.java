package io.perfeccionista.framework.pagefactory.elements.base;

import io.perfeccionista.framework.pagefactory.browser.WebBrowserDispatcher;
import io.perfeccionista.framework.pagefactory.elements.registry.WebElementRegistry;

public interface WebParentElement extends ElementBase, WebLocatorChainAvailable {

    WebBrowserDispatcher getWebBrowserDispatcher();

    WebElementRegistry getElementRegistry();

}
