package io.perfeccionista.framework.pagefactory.jsfunction;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.perfeccionista.framework.pagefactory.keys.KeysEventChain;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

public class SendKeysAsEvents implements JsFunction<Void> {

    private final KeysEventChain keysEventChain;

    public SendKeysAsEvents(@NotNull KeysEventChain keysEventChain) {
        this.keysEventChain = keysEventChain;
    }

    @Override
    public Function<Object, Void> getConverter() {
        return object -> null;
    }

    @Override
    public ObjectNode getJsFunctionInvocation() {
        ObjectNode rootNode = createObjectNode()
                .put("name", getScriptName());
        ObjectNode options = createObjectNode();
        ArrayNode keyEventsNode = options.putArray("valuesToInput");
        keysEventChain.getKeyEvents().forEach(keyEvent -> keyEventsNode.add(keyEvent.toJson()));
        rootNode.set("options", options);
        return rootNode;
    }

    @Override
    public String getScriptName() {
        return "perfeccionista.web.js.SendKeysAsEvents";
    }

    @Override
    public String getScriptDestination() {
        return "js/SendKeysAsEvents.js";
    }

}
