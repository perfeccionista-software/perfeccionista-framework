package io.perfeccionista.framework.pagefactory.jsfunction;

import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.function.Function;

import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

public class GetAttribute implements JsFunction<String> {

    private final String attributeToExtract;

    public GetAttribute(String attributeToExtract) {
        this.attributeToExtract = attributeToExtract;
    }

    @Override
    public Function<Object, String> getConverter() {
        return Object::toString;
    }

    @Override
    public ObjectNode getJsFunctionInvocation() {
        ObjectNode rootNode = createObjectNode()
                .put("name", getScriptName());
        ObjectNode options = createObjectNode()
                .put("attribute", attributeToExtract);
        rootNode.set("options", options);
        return rootNode;
    }

    @Override
    public String getScriptName() {
        return "perfeccionista.web.js.GetAttribute";
    }

    @Override
    public String getScriptDestination() {
        return "js/GetAttribute.js";
    }

}

