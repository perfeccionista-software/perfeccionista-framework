package io.perfeccionista.framework.pagefactory.operation.handler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

public class JsGetIsOnTheScreen implements EndpointHandler<Boolean> {

    private final boolean completely;

    public JsGetIsOnTheScreen(boolean completely) {
        this.completely = completely;
    }

    @Override
    public Boolean handle(Object endpoint) {
        return (Boolean) endpoint;
    }

    @Override
    public @NotNull JsonNode toJson() {
        ObjectNode rootNode = createObjectNode()
                .put("name", "perfeccionista.web.js.GetIsOnTheScreen")
                .put("script", "js/GetIsOnTheScreen.js");
        ObjectNode options = createObjectNode()
                .put("completely", completely);
        rootNode.set("options", options);
        return rootNode;
    }

}
