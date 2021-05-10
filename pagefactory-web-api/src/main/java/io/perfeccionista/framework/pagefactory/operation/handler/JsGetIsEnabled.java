package io.perfeccionista.framework.pagefactory.operation.handler;

import com.fasterxml.jackson.databind.JsonNode;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

public class JsGetIsEnabled implements EndpointHandler<Boolean> {

    @Override
    public Boolean handle(Object endpoint) {
        return (Boolean) endpoint;
    }

    @Override
    public @NotNull JsonNode toJson() {
        return createObjectNode()
                .put("name", "perfeccionista.web.js.GetIsEnabled")
                .put("script", "js/GetIsEnabled.js");
    }

}
