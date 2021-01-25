package io.perfeccionista.framework.pagefactory.operation.handler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.perfeccionista.framework.pagefactory.emulator.keys.KeyEventsChain;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

public class JsSendKeyEvents implements EndpointHandler<Void> {

    private final KeyEventsChain keysEventChain;

    public JsSendKeyEvents(@NotNull KeyEventsChain keysEventChain) {
        this.keysEventChain = keysEventChain;
    }

    @Override
    public Void handle(Object endpoint) {
        return null;
    }

    @Override
    public @NotNull JsonNode toJson() {
        ObjectNode rootNode = createObjectNode()
                .put("name", "perfeccionista.web.js.SendKeysAsEvents")
                .put("script", "js/SendKeysAsEvents.js");
        ObjectNode options = createObjectNode();
        ArrayNode keyEventsNode = options.putArray("valuesToInput");
        keysEventChain.getKeyEvents().forEach(keyEvent -> keyEventsNode.add(keyEvent.toJson()));
        rootNode.set("options", options);
        return rootNode;
    }

}
