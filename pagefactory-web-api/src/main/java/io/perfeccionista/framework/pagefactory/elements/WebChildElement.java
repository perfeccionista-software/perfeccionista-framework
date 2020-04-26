package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.browser.WebBrowserDispatcher;
import io.perfeccionista.framework.pagefactory.elements.base.ChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.HoverToAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.IsInFocusAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.ScrollToAvailable;
import io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionImplementation;
import io.perfeccionista.framework.pagefactory.elements.properties.ElementPropertyAvailable;
import io.perfeccionista.framework.pagefactory.elements.properties.WebElementPropertyHolder;

public interface WebChildElement extends ChildElement<WebParentElement>,
        ElementPropertyAvailable<WebElementPropertyHolder>, HoverToAvailable, ScrollToAvailable, IsInFocusAvailable {

    <T> WebElementActionImplementation<T> getMethodImplementation(String methodType, Class<T> returnType);

    WebBrowserDispatcher getWebBrowserDispatcher();

    WebChildElement executeAction(String name, Object... args);

    WebChildElement executeInteraction(String name, WebChildElement other, Object... args);

}
