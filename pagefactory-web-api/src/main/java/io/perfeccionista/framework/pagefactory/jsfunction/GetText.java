package io.perfeccionista.framework.pagefactory.jsfunction;

import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.function.Function;

import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

public class GetText implements JsFunction<String> {

    @Override
    public Function<Object, String> getConverter() {
        return Object::toString;
    }

    @Override
    public ObjectNode getJsFunctionInvocation() {
        return createObjectNode()
                .put("name", getScriptName());
    }

    @Override
    public String getScriptName() {
        return "perfeccionista.web.js.GetText";
    }

    @Override
    public String getScriptDestination() {
        return "js/GetText.js";
    }

}
