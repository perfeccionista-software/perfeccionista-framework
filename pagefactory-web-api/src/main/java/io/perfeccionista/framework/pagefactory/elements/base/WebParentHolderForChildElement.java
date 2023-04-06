package io.perfeccionista.framework.pagefactory.elements.base;

import io.perfeccionista.framework.pagefactory.elements.selectors.WebSelectorChain;
import org.jetbrains.annotations.NotNull;

public class WebParentHolderForChildElement implements WebParentHolder {

    private final WebParentElement parentBlock;

    private WebParentHolderForChildElement(WebParentElement parentBlock) {
        this.parentBlock = parentBlock;
    }

    public static WebParentHolderForChildElement of(@NotNull WebParentElement parent) {
        return new WebParentHolderForChildElement(parent);
    }

    @Override
    public @NotNull WebElementBase getParent() {
        return parentBlock;
    }

    @Override
    public @NotNull WebSelectorChain getSelectorChain() {
        return parentBlock.getSelectorChain();
    }

}
