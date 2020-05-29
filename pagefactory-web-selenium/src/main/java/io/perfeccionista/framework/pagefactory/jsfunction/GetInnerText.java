package io.perfeccionista.framework.pagefactory.jsfunction;

import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.function.Function;

import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

public class GetInnerText implements JsFunction<String> {

    @Override
    public Function<Object, String> getConverter() {
        return Object::toString;
    }

    @Override
    public ObjectNode getJsFunctionInvocation() {
        return createObjectNode()
                .put("name", "perfeccionista.js.selenium.GetInnerText");
    }

    @Override
    public String getScriptName() {
        return "perfeccionista.js.selenium.GetInnerText";
    }

    @Override
    public String getScriptDestination() {
        return "js/GetInnerText.js";
    }

}
