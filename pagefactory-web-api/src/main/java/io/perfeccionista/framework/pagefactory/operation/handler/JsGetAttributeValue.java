package io.perfeccionista.framework.pagefactory.operation.handler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

public class JsGetAttributeValue implements EndpointHandler<String> {

    private final String attributeToExtract;

    public JsGetAttributeValue(@NotNull String attributeToExtract) {
        this.attributeToExtract = attributeToExtract;
    }

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
                .put("attribute", attributeToExtract);
        rootNode.set("options", options);
        return rootNode;
    }

}
