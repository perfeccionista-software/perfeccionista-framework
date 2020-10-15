package io.perfeccionista.framework.pagefactory.factory.proxy.frame;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.perfeccionista.framework.pagefactory.elements.registry.WebElementRegistry;
import org.jetbrains.annotations.NotNull;

public class WebBlockElementFrame extends WebChildElementFrame {

    protected WebElementRegistry elementRegistry;

    public @NotNull WebElementRegistry getElementRegistry() {
        return elementRegistry;
    }

    @Override
    public JsonNode toJson() {
        ObjectNode rootNode = (ObjectNode) super.toJson();
        rootNode.set("elements", elementRegistry.toJson());
        return rootNode;
    }

}
