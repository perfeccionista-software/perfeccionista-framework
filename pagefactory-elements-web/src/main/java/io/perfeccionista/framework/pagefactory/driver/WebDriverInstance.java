package io.perfeccionista.framework.pagefactory.driver;

import io.perfeccionista.framework.pagefactory.driver.context.WebPageContext;
import io.perfeccionista.framework.pagefactory.js.JsRepository;
import io.perfeccionista.framework.pagefactory.operations.DriverOperationExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;

public interface WebDriverInstance extends DriverInstance<RemoteWebDriver> {

    DriverOperationExecutor getDriverOperationExecutor();

    JsRepository getJsRepository();

    // Возможно, сюда нужно будет передавать экземпляр сервиса WebPageService
    WebPageContext getPageContext();

    // Tabs
    WebDriverInstance openUrl(String url);



}
