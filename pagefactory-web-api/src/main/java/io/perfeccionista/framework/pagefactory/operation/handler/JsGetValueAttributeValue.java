package io.perfeccionista.framework.pagefactory.operation.handler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

public class JsGetValueAttributeValue implements EndpointHandler<String> {

    @Override
    public @Nullable String handle(Object endpoint) {
        return Objects.isNull(endpoint) ? null : String.valueOf(endpoint);
    }

    @Override
    public @NotNull JsonNode toJson() {
        ObjectNode rootNode = createObjectNode()
                .put("name", "perfeccionista.web.js.GetAttribute")
                .put("script", "js/GetAttribute.js");
        ObjectNode options = createObjectNode()
                .put("attribute", "value");
        rootNode.set("options", options);
        return rootNode;
    }

}
