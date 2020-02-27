package io.perfeccionista.framework.pagefactory.elements.web;

import io.perfeccionista.framework.pagefactory.driver.DriverInstance;
import io.perfeccionista.framework.pagefactory.elements.base.ChildElement;
import io.perfeccionista.framework.pagefactory.elements.locators.LocatorChain;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.HoverToAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.ScrollToAvailable;
import io.perfeccionista.framework.pagefactory.elements.web.methods.WebElementMethodImplementation;
import org.openqa.selenium.remote.RemoteWebDriver;

public interface WebChildElement extends ChildElement<WebParentElement>, HoverToAvailable, ScrollToAvailable {

    <T> WebElementMethodImplementation<T> getMethodImplementation(String methodType, Class<T> returnType);

    DriverInstance<RemoteWebDriver> getDriverInstance();

    LocatorChain getLocatorChainTo(String locatorName);

    LocatorChain getLocatorChain();

}
