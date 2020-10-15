package io.perfeccionista.framework.pagefactory.factory.proxy.frame;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.perfeccionista.framework.name.WebElementIdentifier;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

public class WebChildElementFrame implements WebElementFrame<WebChildElement> {

    protected Class<? extends WebChildElement> elementClass;
    protected WebElementIdentifier elementIdentifier;

    @Override
    public @NotNull Class<? extends WebChildElement> getElementClass() {
        return elementClass;
    }

    public @NotNull WebElementIdentifier getElementIdentifier() {
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

