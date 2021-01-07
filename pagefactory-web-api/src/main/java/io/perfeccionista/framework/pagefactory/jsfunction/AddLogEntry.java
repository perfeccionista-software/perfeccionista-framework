package io.perfeccionista.framework.pagefactory.jsfunction;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;
import java.util.logging.Level;

import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

public class AddLogEntry implements JsFunction<Void> {

    private final Level logLevel;
    private final String valueToInput;

    public AddLogEntry(@NotNull Level logLevel, @NotNull String valueToInput) {
        this.logLevel = logLevel;
        this.valueToInput = valueToInput;
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
                .put("logLevel", logLevel.getName())
                .put("valueToInput", valueToInput);
        rootNode.set("options", options);
        return rootNode;
    }

    @Override
    public String getScriptName() {
        return "perfeccionista.web.js.AddLogEntry";
    }

    @Override
    public String getScriptDestination() {
        return "js/AddLogEntry.js";
    }

}
