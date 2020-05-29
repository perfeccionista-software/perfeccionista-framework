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
                .put("name", "perfeccionista.js.selenium.GetText");
    }

    @Override
    public String getScriptName() {
        return "perfeccionista.js.selenium.GetText";
    }

    @Override
    public String getScriptDestination() {
        return "js/GetText.js";
    }

}
