package io.perfeccionista.framework.pagefactory.elements.mapping;

import io.perfeccionista.framework.pagefactory.elements.WebBlock;

public class WebListFrame<T extends WebBlock> {

    private final T mappedBlockFrame;

    public WebListFrame(T mappedBlockFrame) {
        this.mappedBlockFrame = mappedBlockFrame;
    }

    public T getMappedBlockFrame() {
        return mappedBlockFrame;
    }

}
