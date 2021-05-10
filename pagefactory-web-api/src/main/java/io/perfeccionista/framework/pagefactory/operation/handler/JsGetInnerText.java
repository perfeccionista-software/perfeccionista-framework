package io.perfeccionista.framework.pagefactory.operation.handler;

import com.fasterxml.jackson.databind.JsonNode;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

public class JsGetInnerText implements EndpointHandler<String> {

    @Override
    public @NotNull String handle(Object endpoint) {
        return Objects.isNull(endpoint) ? "" : String.valueOf(endpoint);
    }

    @Override
    public @NotNull JsonNode toJson() {
        return createObjectNode()
                .put("name", "perfeccionista.web.js.GetInnerText")
                .put("script", "js/GetInnerText.js");
    }

}
