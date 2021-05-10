package io.perfeccionista.framework.pagefactory.operation.handler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;

import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

public class JsTypeText implements EndpointHandler<Void> {

    private final String valueToInput;
    private Duration delay;

    public JsTypeText(@NotNull String valueToInput) {
        this.valueToInput = valueToInput;
        this.delay = Duration.ZERO;
    }

    public JsTypeText setDelay(@NotNull Duration delay) {
        this.delay = delay;
        return this;
    }

    @Override
    public Void handle(Object endpoint) {
        return null;
    }

    @Override
    public @NotNull JsonNode toJson() {
        ObjectNode rootNode = createObjectNode()
                .put("name", "perfeccionista.web.js.SendKeysAsText")
                .put("script", "js/SendKeysAsText.js");
        ObjectNode options = createObjectNode()
                .put("valueToInput", valueToInput)
                .put("delay", delay.toMillis());
        rootNode.set("options", options);
        return rootNode;
    }

}
