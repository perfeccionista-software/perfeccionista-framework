package io.perfeccionista.framework.pagefactory.jsfunction;

import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.function.Function;

import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

public class GetIsInFocus implements JsFunction<Boolean> {

    @Override
    public Function<Object, Boolean> getConverter() {
        return object -> (Boolean) object;
    }

    @Override
    public ObjectNode getJsFunctionInvocation() {
        return createObjectNode()
                .put("name", getScriptName());
    }

    @Override
    public String getScriptName() {
        return "perfeccionista.web.js.GetIsInFocus";
    }

    @Override
    public String getScriptDestination() {
        return "js/GetIsInFocus.js";
    }

}
