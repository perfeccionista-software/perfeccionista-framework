package io.perfeccionista.framework.pagefactory.elements.web;

import io.perfeccionista.framework.pagefactory.driver.DriverInstance;
import io.perfeccionista.framework.pagefactory.elements.base.ChildElement;
import io.perfeccionista.framework.pagefactory.elements.web.methods.WebElementMethodImplementation;
import org.openqa.selenium.remote.RemoteWebDriver;

public interface WebChildElement extends ChildElement<WebParentElement> {

    DriverInstance<RemoteWebDriver> getDriverInstance();

    <T> WebElementMethodImplementation<T> getMethodImplementation(String methodType, Class<T> returnType);

}
