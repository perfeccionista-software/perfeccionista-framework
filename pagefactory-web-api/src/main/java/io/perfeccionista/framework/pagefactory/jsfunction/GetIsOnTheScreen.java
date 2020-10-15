package io.perfeccionista.framework.pagefactory.jsfunction;

import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.function.Function;

import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

public class GetIsOnTheScreen implements JsFunction<Boolean> {

    private final boolean completely;

    public GetIsOnTheScreen(boolean completely) {
        this.completely = completely;
    }

    @Override
    public Function<Object, Boolean> getConverter() {
        return object -> (Boolean) object;
    }

    @Override
    public ObjectNode getJsFunctionInvocation() {
        ObjectNode rootNode = createObjectNode()
                .put("name", getScriptName());
        ObjectNode options = createObjectNode()
                .put("completely", completely);
        rootNode.set("options", options);
        return rootNode;
    }

    @Override
    public String getScriptName() {
        return "perfeccionista.web.js.GetIsOnTheScreen";
    }

    @Override
    public String getScriptDestination() {
        return "js/GetIsOnTheScreen.js";
    }

}
