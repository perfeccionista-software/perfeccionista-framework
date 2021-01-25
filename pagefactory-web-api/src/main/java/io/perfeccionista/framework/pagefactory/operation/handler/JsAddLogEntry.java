package io.perfeccionista.framework.pagefactory.operation.handler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Level;

import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

public class JsAddLogEntry implements EndpointHandler<Void> {

    private final Level logLevel;
    private final String valueToInput;

    public JsAddLogEntry(@NotNull Level logLevel, @NotNull String valueToInput) {
        this.logLevel = logLevel;
        this.valueToInput = valueToInput;
    }

    @Override
    public Void handle(Object endpoint) {
        return null;
    }

    @Override
    public @NotNull JsonNode toJson() {
        ObjectNode options = createObjectNode()
                .put("logLevel", logLevel.getName())
                .put("valueToInput", valueToInput);
        return  createObjectNode()
                .put("name", "perfeccionista.web.js.AddLogEntry")
                .put("script", "js/AddLogEntry.js")
                .set("options", options);
    }
}
