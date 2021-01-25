package io.perfeccionista.framework.pagefactory.operation.handler;

import com.fasterxml.jackson.databind.JsonNode;
import io.perfeccionista.framework.pagefactory.elements.ElementBounds;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;
import static io.perfeccionista.framework.utils.JsonUtils.parseJsonNode;

public class JsGetElementBounds implements EndpointHandler<ElementBounds> {

    @Override
    public ElementBounds handle(Object endpoint) {
        JsonNode elementBoundsNode = parseJsonNode(endpoint.toString());
        return ElementBounds.of(
                elementBoundsNode.get("width").asDouble(),
                elementBoundsNode.get("height").asDouble(),
                elementBoundsNode.get("screenLeft").asDouble(),
                elementBoundsNode.get("screenTop").asDouble(),
                elementBoundsNode.get("absoluteLeft").asDouble(),
                elementBoundsNode.get("absoluteTop").asDouble(),
                elementBoundsNode.get("centerX").asDouble(),
                elementBoundsNode.get("centerY").asDouble()
        );
    }

    @Override
    public @NotNull JsonNode toJson() {
        return createObjectNode()
                .put("name", "perfeccionista.web.js.GetElementBounds")
                .put("script", "js/GetElementBounds.js");
    }

}
