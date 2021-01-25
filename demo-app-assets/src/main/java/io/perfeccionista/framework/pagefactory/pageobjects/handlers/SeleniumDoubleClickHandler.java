package io.perfeccionista.framework.pagefactory.pageobjects.handlers;

import com.fasterxml.jackson.databind.JsonNode;
import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.pagefactory.dispatcher.WebBrowserService;
import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;

import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

public class SeleniumDoubleClickHandler implements EndpointHandler<Void> {

    @Override
    public Void handle(Object endpoint) {
        WebElement webElement = (WebElement) endpoint;
        RemoteWebDriver webDriver = Environment.getCurrent().getService(WebBrowserService.class)
                .getActiveDispatcher()
                .getInstance(RemoteWebDriver.class);
        new Actions(webDriver).doubleClick(webElement).perform();
        return null;
    }

    @Override
    public @NotNull JsonNode toJson() {
        return createObjectNode()
                .put("name", "perfeccionista.js.selenium.GetWebElement")
                .put("script", "js/GetWebElement.js");
    }

}
