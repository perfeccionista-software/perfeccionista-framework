package io.perfeccionista.framework.pagefactory.elements.mapping;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.perfeccionista.framework.json.JsonSerializable;
import io.perfeccionista.framework.pagefactory.elements.DefaultWebRadioButtonBlock;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

public class WebRadioGroupFrame<T extends DefaultWebRadioButtonBlock> implements JsonSerializable {

    private final T mappedBlockFrame;

    public WebRadioGroupFrame(@NotNull T mappedBlockFrame) {
        this.mappedBlockFrame = mappedBlockFrame;
    }

    public @NotNull T getMappedBlockFrame() {
        return mappedBlockFrame;
    }

    public @NotNull Class<T> getMappedBlockClass() {
        return (Class<T>) mappedBlockFrame.getClass();
    }

    @Override
    public @NotNull JsonNode toJson() {
        ObjectNode rootNode = createObjectNode();
        rootNode.put("mapped block", this.mappedBlockFrame.getClass().getCanonicalName());
        return rootNode;
    }

    @Override
    public String toString() {
        return toJson().toString();
    }

}
