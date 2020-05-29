package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.browser.WebBrowserDispatcher;
import io.perfeccionista.framework.pagefactory.elements.base.ChildElement;
import io.perfeccionista.framework.pagefactory.elements.components.WebElementComponentAvailable;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import io.perfeccionista.framework.pagefactory.elements.methods.HoverToAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.IsInFocusAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.ScrollToAvailable;
import io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionImplementation;
import io.perfeccionista.framework.pagefactory.elements.properties.WebElementPropertyAvailable;
import io.perfeccionista.framework.pagefactory.elements.properties.WebElementPropertyHolder;

public interface WebChildElement extends ChildElement<WebParentElement>,
        WebElementComponentAvailable, WebElementPropertyAvailable<WebElementPropertyHolder>,
        HoverToAvailable, ScrollToAvailable, IsInFocusAvailable {

    <R> WebElementActionImplementation<R> getMethodImplementation(String methodType, Class<R> returnType);

    WebBrowserDispatcher getWebBrowserDispatcher();

    WebLocatorHolder getLocator(String locatorName);

    WebLocatorChain getLocatorChainTo(String locatorName);

    WebLocatorChain getLocatorChain();

    WebChildElement executeAction(String name, Object... args);

    WebChildElement executeInteraction(String name, WebChildElement other, Object... args);

}
