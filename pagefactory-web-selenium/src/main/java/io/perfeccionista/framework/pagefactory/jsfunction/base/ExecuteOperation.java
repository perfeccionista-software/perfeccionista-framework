package io.perfeccionista.framework.pagefactory.jsfunction.base;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.perfeccionista.framework.pagefactory.jsfunction.JsFunction;

import java.util.function.Function;

import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

public class ExecuteOperation implements JsFunction<Void> {

    @Override
    public Function<Object, Void> getConverter() {
        return object -> null;
    }

    @Override
    public ObjectNode getJsFunctionInvocation() {
        return createObjectNode()
                .put("name", "perfeccionista.js.selenium.ExecuteOperation");
    }

    @Override
    public String getScriptName() {
        return "perfeccionista.js.selenium.ExecuteOperation";
    }

    @Override
    public String getScriptDestination() {
        return "js/base/ExecuteOperation.js";
    }

}
