package io.perfeccionista.framework.pagefactory.driver;

import io.perfeccionista.framework.pagefactory.js.JsRepository;
import io.perfeccionista.framework.pagefactory.operations.DriverOperationExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;

public interface WebDriverInstance extends DriverInstance<RemoteWebDriver> {

    JsRepository getJsRepository();

    DriverOperationExecutor getDriverOperationExecutor();

}
