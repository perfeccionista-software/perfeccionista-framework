package io.perfeccionista.framework.pagefactory.operation.handler;

import com.fasterxml.jackson.databind.JsonNode;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebElement;

import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

public class SeleniumClear implements EndpointHandler<Void> {

    @Override
    public Void handle(Object endpoint) {
        ((WebElement) endpoint).clear();
        return null;
    }

    @Override
    public @NotNull JsonNode toJson() {
        return createObjectNode()
                .put("name", "perfeccionista.js.selenium.GetWebElement")
                .put("script", "js/GetWebElement.js");
    }

}
