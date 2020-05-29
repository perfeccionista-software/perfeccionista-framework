package io.perfeccionista.framework.pagefactory.jsfunction;

import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.function.Function;

import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

public class GetSize implements JsFunction<Integer> {

    @Override
    public Function<Object, Integer> getConverter() {
        return object -> (Integer) object;
    }

    @Override
    public ObjectNode getJsFunctionInvocation() {
        return createObjectNode()
                .put("name", "perfeccionista.js.selenium.GetSize");
    }

    @Override
    public String getScriptName() {
        return "perfeccionista.js.selenium.GetSize";
    }

    @Override
    public String getScriptDestination() {
        return "js/GetSize.js";
    }

}
