package io.perfeccionista.framework.pagefactory.factory.proxy.frame;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.perfeccionista.framework.name.MobileElementIdentifier;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElement;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

public class MobileChildElementFrame implements MobileElementFrame<MobileChildElement> {

    protected Class<? extends MobileChildElement> elementClass;
    protected MobileElementIdentifier elementIdentifier;

    @Override
    public @NotNull Class<? extends MobileChildElement> getElementClass() {
        return elementClass;
    }

    public @NotNull MobileElementIdentifier getElementIdentifier() {
        return elementIdentifier;
    }

    @Override
    public JsonNode toJson() {
        ObjectNode rootNode = createObjectNode();
        rootNode.set("elementIdentifier", elementIdentifier.toJson());
        rootNode.put("elementClass", elementClass.getCanonicalName());
        return rootNode;
    }

}


