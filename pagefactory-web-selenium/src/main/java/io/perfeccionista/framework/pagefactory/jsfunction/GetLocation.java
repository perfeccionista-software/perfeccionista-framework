package io.perfeccionista.framework.pagefactory.jsfunction;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.perfeccionista.framework.pagefactory.elements.methods.Location;

import java.util.function.Function;

import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;
import static io.perfeccionista.framework.utils.JsonUtils.parseJsonNode;

public class GetLocation implements JsFunction<Location> {

    @Override
    public Function<Object, Location> getConverter() {
        return object -> {
            JsonNode locationNode = parseJsonNode(object.toString());
            return Location.of(
                    locationNode.get("pageX").asDouble(),
                    locationNode.get("pageY").asDouble(),
                    locationNode.get("absolutePageX").asDouble(),
                    locationNode.get("absolutePageY").asDouble(),
                    locationNode.get("elementCenterX").asDouble(),
                    locationNode.get("elementCenterY").asDouble()
            );
        };
    }

    @Override
    public ObjectNode getJsFunctionInvocation() {
        return createObjectNode()
                .put("name", "perfeccionista.js.selenium.GetLocation");
    }

    @Override
    public String getScriptName() {
        return "perfeccionista.js.selenium.GetLocation";
    }

    @Override
    public String getScriptDestination() {
        return "js/GetLocation.js";
    }

}
