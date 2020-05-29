package io.perfeccionista.framework.pagefactory.jsfunction;

import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.function.Function;

import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

// TODO: В опциях можно передавать координаты или сдвиг для точки клика
public class Click implements JsFunction<Void> {

    @Override
    public Function<Object, Void> getConverter() {
        return object -> null;
    }

    @Override
    public ObjectNode getJsFunctionInvocation() {
        return createObjectNode()
                .put("name", "perfeccionista.js.selenium.Click");
    }

    @Override
    public String getScriptName() {
        return "perfeccionista.js.selenium.Click";
    }

    @Override
    public String getScriptDestination() {
        return "js/Click.js";
    }

}
