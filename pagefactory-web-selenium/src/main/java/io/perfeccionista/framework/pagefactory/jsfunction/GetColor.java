package io.perfeccionista.framework.pagefactory.jsfunction;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.perfeccionista.framework.plugin.Color;
import io.perfeccionista.framework.plugin.WebElementColor;

import java.util.function.Function;

import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;
import static io.perfeccionista.framework.utils.JsonUtils.parseJsonNode;

public class GetColor implements JsFunction<Color> {

    private final String cssProperty;

    public GetColor(String cssProperty) {
        this.cssProperty = cssProperty;
    }

    @Override
    public Function<Object, Color> getConverter() {
        return object -> {
            JsonNode colorNode = parseJsonNode(object.toString());
            return new WebElementColor(
                    colorNode.get("r").asInt(),
                    colorNode.get("g").asInt(),
                    colorNode.get("b").asInt(),
                    colorNode.get("alpha").asDouble()
            );
        };
    }

    @Override
    public ObjectNode getJsFunctionInvocation() {
        ObjectNode rootNode = createObjectNode()
                .put("name", "perfeccionista.js.selenium.GetColor");
        ObjectNode options = createObjectNode()
                .put("cssProperty", cssProperty);
        rootNode.set("options", options);
        return rootNode;
    }

    @Override
    public String getScriptName() {
        return "perfeccionista.js.selenium.GetColor";
    }

    @Override
    public String getScriptDestination() {
        return "js/GetColor.js";
    }

}
