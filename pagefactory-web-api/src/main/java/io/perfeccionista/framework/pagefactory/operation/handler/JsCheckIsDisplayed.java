package io.perfeccionista.framework.pagefactory.operation.handler;

import com.fasterxml.jackson.databind.JsonNode;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

public class JsCheckIsDisplayed implements EndpointHandler<Void> {

    @Override
    public Void handle(Object endpoint) {
        return null;
    }

    @Override
    public @NotNull JsonNode toJson() {
        return createObjectNode()
                .put("name", "perfeccionista.web.js.CheckIsDisplayed")
                .put("script", "js/CheckIsDisplayed.js");
    }

}
