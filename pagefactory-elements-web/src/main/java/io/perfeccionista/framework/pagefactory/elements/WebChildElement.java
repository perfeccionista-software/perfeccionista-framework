package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.driver.WebDriverInstance;
import io.perfeccionista.framework.pagefactory.elements.base.ChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.HoverToAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.ScrollToAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebElementMethodImplementation;
import io.perfeccionista.framework.pagefactory.elements.properties.ElementPropertyAvailable;
import io.perfeccionista.framework.pagefactory.elements.properties.WebElementPropertyHolder;

public interface WebChildElement extends ChildElement<WebParentElement>,
        ElementPropertyAvailable<WebElementPropertyHolder>, HoverToAvailable, ScrollToAvailable {

    <T> WebElementMethodImplementation<T> getMethodImplementation(String methodType, Class<T> returnType);

    WebDriverInstance getDriverInstance();

}
