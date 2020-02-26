package io.perfeccionista.framework.pagefactory.elements.web;

import io.perfeccionista.framework.pagefactory.driver.DriverInstance;
import io.perfeccionista.framework.pagefactory.elements.base.ParentElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public interface WebParentElement extends ParentElement<WebChildElement> {

    DriverInstance<RemoteWebDriver> getDriverInstance();

}
