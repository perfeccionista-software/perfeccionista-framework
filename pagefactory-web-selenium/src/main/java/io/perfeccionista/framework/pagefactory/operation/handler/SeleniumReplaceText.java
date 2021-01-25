package io.perfeccionista.framework.pagefactory.operation.handler;

import com.fasterxml.jackson.databind.JsonNode;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebElement;

import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

public class SeleniumReplaceText implements EndpointHandler<Void> {

    private final String valueToInput;

    public SeleniumReplaceText(@NotNull String valueToInput) {
        this.valueToInput = valueToInput;
    }

    @Override
    public Void handle(Object endpoint) {
        WebElement webElement = (WebElement) endpoint;
        webElement.clear();
        webElement.sendKeys(valueToInput);
        return null;
    }

    @Override
    public @NotNull JsonNode toJson() {
        return createObjectNode()
                .put("name", "perfeccionista.js.selenium.GetWebElement")
                .put("script", "js/GetWebElement.js");
    }
}
