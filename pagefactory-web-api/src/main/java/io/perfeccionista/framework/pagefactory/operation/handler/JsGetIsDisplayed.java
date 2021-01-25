package io.perfeccionista.framework.pagefactory.operation.handler;

import com.fasterxml.jackson.databind.JsonNode;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

public class JsGetIsDisplayed implements EndpointHandler<Boolean> {

    @Override
    public Boolean handle(Object endpoint) {
        return Objects.isNull(endpoint) ? false : (Boolean) endpoint;
    }

    @Override
    public @NotNull JsonNode toJson() {
        return createObjectNode()
                .put("name", "perfeccionista.web.js.GetIsDisplayed")
                .put("script", "js/GetIsDisplayed.js");
    }

}
