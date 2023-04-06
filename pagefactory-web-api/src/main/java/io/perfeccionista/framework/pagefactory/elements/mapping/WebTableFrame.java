package io.perfeccionista.framework.pagefactory.elements.mapping;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import org.jetbrains.annotations.NotNull;

public class WebTableFrame<H extends WebBlock, T extends WebBlock> extends WebListFrame<T> {

    protected final H mappedHeader;

    public WebTableFrame(@NotNull H mappedHeader,
                         @NotNull T mappedBlockFrame) {
        super(mappedBlockFrame);
        this.mappedHeader = mappedHeader;
    }

    public @NotNull H getMappedHeader() {
        return mappedHeader;
    }

    public @NotNull Class<H> getMappedHeaderClass() {
        return (Class<H>) mappedHeader.getClass();
    }

    @Override
    public @NotNull JsonNode toJson() {
        ObjectNode rootNode = (ObjectNode) super.toJson();
        rootNode.put("mapped header", this.mappedHeader.getClass().getCanonicalName());
        return rootNode;
    }

    @Override
    public String toString() {
        return toJson().toString();
    }

}
