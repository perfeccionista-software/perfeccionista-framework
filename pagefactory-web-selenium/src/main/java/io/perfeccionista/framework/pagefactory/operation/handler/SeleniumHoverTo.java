package io.perfeccionista.framework.pagefactory.operation.handler;

import com.fasterxml.jackson.databind.JsonNode;
import io.perfeccionista.framework.measurements.Point2D;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.WebHoverToAvailable;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;

import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

public class SeleniumHoverTo implements EndpointHandler<Void> {

    private final WebHoverToAvailable element;
    private final boolean withOutOfBounds;

    public SeleniumHoverTo(@NotNull WebHoverToAvailable element, boolean withOutOfBounds) {
        this.element = element;
        this.withOutOfBounds = withOutOfBounds;
    }

    @Override
    public Void handle(Object endpoint) {
        WebElement webElement = (WebElement) endpoint;

        RemoteWebDriver webDriver = element.getWebBrowserDispatcher().getInstance(RemoteWebDriver.class);

        if (withOutOfBounds) {
            Point2D location = ((WebChildElement) element).getElementBounds().getScreenLocation();

            //noinspection ConstantConditions because Location from element already filled
            double xShift = -(location.getX() - 10);
            double yShift = 0;

            new Actions(webDriver).moveToElement(webElement, (int) xShift, (int) yShift).build().perform();
        }
        new Actions(webDriver).moveToElement(webElement).build().perform();
        return null;
    }

    @Override
    public @NotNull JsonNode toJson() {
        return createObjectNode()
                .put("name", "perfeccionista.js.selenium.GetWebElement")
                .put("script", "js/GetWebElement.js");
    }
}

