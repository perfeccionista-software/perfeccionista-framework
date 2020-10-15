package io.perfeccionista.framework.pagefactory.jsfunction;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.perfeccionista.framework.measurements.Dimensions;

import java.util.function.Function;

import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;
import static io.perfeccionista.framework.utils.JsonUtils.parseJsonNode;

public class GetDimensions implements JsFunction<Dimensions> {

    @Override
    public Function<Object, Dimensions> getConverter() {
        return object -> {
            JsonNode dimensionsNode = parseJsonNode(object.toString());
            return Dimensions.of(dimensionsNode.get("width").asDouble(), dimensionsNode.get("height").asDouble());
        };
    }

    @Override
    public ObjectNode getJsFunctionInvocation() {
        return createObjectNode()
                .put("name", getScriptName());
    }

    @Override
    public String getScriptName() {
        return "perfeccionista.web.js.GetDimensions";
    }

    @Override
    public String getScriptDestination() {
        return "js/GetDimensions.js";
    }

}
