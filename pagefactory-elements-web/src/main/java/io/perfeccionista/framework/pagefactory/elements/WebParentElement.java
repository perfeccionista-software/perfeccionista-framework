package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.driver.WebDriverInstance;
import io.perfeccionista.framework.pagefactory.elements.base.ParentElement;
import io.perfeccionista.framework.pagefactory.elements.registry.WebElementRegistry;

public interface WebParentElement extends ParentElement<WebChildElement> {

    WebDriverInstance getDriverInstance();

    WebElementRegistry getElementRegistry();

}
