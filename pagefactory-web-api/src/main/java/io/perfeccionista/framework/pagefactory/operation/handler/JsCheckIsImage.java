package io.perfeccionista.framework.pagefactory.operation.handler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

public class JsCheckIsImage implements EndpointHandler<Boolean> {

    @Override
    public @NotNull JsonNode toJson() {
        ObjectNode rootNode = createObjectNode()
                .put("name", "perfeccionista.web.js.GetAttribute")
                .put("script", "js/GetAttribute.js");
        ObjectNode options = createObjectNode()
                .put("attribute", "src");
        rootNode.set("options", options);
        return rootNode;
    }

    @Override
    public Boolean handle(Object endpoint) {
        return Objects.nonNull(endpoint) && endpoint.toString().length() > 0;
    }

}
