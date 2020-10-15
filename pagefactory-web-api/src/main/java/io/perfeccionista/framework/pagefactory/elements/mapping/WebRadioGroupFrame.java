package io.perfeccionista.framework.pagefactory.elements.mapping;

import io.perfeccionista.framework.pagefactory.elements.WebBlock;

public class WebRadioGroupFrame<T extends WebBlock> {

    private final T mappedBlockFrame;

    public WebRadioGroupFrame(T mappedBlockFrame) {
        this.mappedBlockFrame = mappedBlockFrame;
    }

    public T getMappedBlockFrame() {
        return mappedBlockFrame;
    }

}
