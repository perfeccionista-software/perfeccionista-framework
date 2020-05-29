package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.browser.WebBrowserDispatcher;
import io.perfeccionista.framework.pagefactory.elements.base.ParentElement;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;

public interface WebParentElement extends ParentElement<WebChildElement> {

    WebBrowserDispatcher getDriverInstance();

    WebLocatorHolder getLocator(String locatorName);

}
