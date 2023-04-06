package io.perfeccionista.framework.pagefactory.elements.mapping;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.perfeccionista.framework.json.JsonSerializable;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

public class WebListFrame<T extends WebBlock> implements JsonSerializable {

    protected final T mappedItemFrame;

    public WebListFrame(@NotNull T mappedItemFrame) {
        this.mappedItemFrame = mappedItemFrame;
    }

    public @NotNull T getMappedItemFrame() {
        return mappedItemFrame;
    }

    public @NotNull Class<T> getMappedItemClass() {
        return (Class<T>) mappedItemFrame.getClass();
    }

    @Override
    public @NotNull JsonNode toJson() {
        ObjectNode rootNode = createObjectNode();
        rootNode.put("mapped item", this.mappedItemFrame.getClass().getCanonicalName());
        return rootNode;
    }

    @Override
    public String toString() {
        return toJson().toString();
    }

}
