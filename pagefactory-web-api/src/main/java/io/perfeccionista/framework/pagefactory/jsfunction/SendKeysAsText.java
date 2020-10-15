package io.perfeccionista.framework.pagefactory.jsfunction;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;
import java.util.function.Function;

import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

public class SendKeysAsText implements JsFunction<Void> {

    private final String valueToInput;
    private final Duration delay;

    public SendKeysAsText(@NotNull String valueToInput) {
        this.valueToInput = valueToInput;
        this.delay = Duration.ZERO;
    }

    public SendKeysAsText(@NotNull String valueToInput, @NotNull Duration delay) {
        this.valueToInput = valueToInput;
        this.delay = delay;
    }

    @Override
    public Function<Object, Void> getConverter() {
        return object -> null;
    }

    @Override
    public ObjectNode getJsFunctionInvocation() {
        ObjectNode rootNode = createObjectNode()
                .put("name", getScriptName());
        ObjectNode options = createObjectNode()
                .put("valueToInput", valueToInput)
                .put("delay", delay.toMillis());
        rootNode.set("options", options);
        return rootNode;
    }

    @Override
    public String getScriptName() {
        return "perfeccionista.web.js.SendKeysAsText";
    }

    @Override
    public String getScriptDestination() {
        return "js/SendKeysAsText.js";
    }

}