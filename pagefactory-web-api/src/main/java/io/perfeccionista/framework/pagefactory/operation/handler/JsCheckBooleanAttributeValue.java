package io.perfeccionista.framework.pagefactory.operation.handler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

public class JsCheckBooleanAttributeValue implements EndpointHandler<Boolean> {

    private final WebChildElement element;
    private final String attributeName;
    private final boolean expectedValue;

    public JsCheckBooleanAttributeValue(@NotNull WebChildElement element, @NotNull String attributeName, boolean expectedValue) {
        this.element = element;
        this.attributeName = attributeName;
        this.expectedValue = expectedValue;
    }

    @Override
    public @NotNull JsonNode toJson() {
        ObjectNode rootNode = createObjectNode()
                .put("name", "perfeccionista.web.js.GetAttribute")
                .put("script", "js/GetAttribute.js");
        ObjectNode options = createObjectNode()
                .put("attribute", attributeName);
        rootNode.set("options", options);
        return rootNode;
    }

    @Override
    public Boolean handle(Object endpoint) {
        return Objects.isNull(endpoint)
                ? false
                : expectedValue == Boolean.valueOf(endpoint.toString());
    }

}

