package io.perfeccionista.framework.pagefactory.operation.handler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.perfeccionista.framework.color.Color;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;
import static io.perfeccionista.framework.utils.JsonUtils.parseJsonNode;

public class JsGetColor implements EndpointHandler<Color> {

    private final String cssProperty;

    public JsGetColor(@NotNull String cssProperty) {
        this.cssProperty = cssProperty;
    }

    @Override
    public Color handle(Object endpoint) {
        JsonNode colorNode = parseJsonNode(endpoint.toString());
        return Color.of(
                colorNode.get("r").asInt(),
                colorNode.get("g").asInt(),
                colorNode.get("b").asInt(),
                colorNode.get("alpha").asDouble()
        );
    }

    @Override
    public @NotNull JsonNode toJson() {
        ObjectNode rootNode = createObjectNode()
                .put("name", "perfeccionista.web.js.GetColor")
                .put("script", "js/GetColor.js");
        ObjectNode options = createObjectNode()
                .put("cssProperty", cssProperty);
        rootNode.set("options", options);
        return rootNode;
    }

}
