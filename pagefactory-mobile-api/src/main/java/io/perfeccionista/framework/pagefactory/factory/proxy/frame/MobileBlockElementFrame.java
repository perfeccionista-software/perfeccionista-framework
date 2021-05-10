package io.perfeccionista.framework.pagefactory.factory.proxy.frame;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.perfeccionista.framework.pagefactory.elements.registry.MobileElementRegistry;
import org.jetbrains.annotations.NotNull;

public class MobileBlockElementFrame extends MobileChildElementFrame {

    protected MobileElementRegistry elementRegistry;

    public @NotNull MobileElementRegistry getElementRegistry() {
        return elementRegistry;
    }

    @Override
    public JsonNode toJson() {
        ObjectNode rootNode = (ObjectNode) super.toJson();
        rootNode.set("elements", elementRegistry.toJson());
        return rootNode;
    }

}
