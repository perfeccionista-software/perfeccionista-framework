package io.perfeccionista.framework.pagefactory.elements.base;

import io.perfeccionista.framework.pagefactory.elements.locators.MobileLocatorChain;
import org.jetbrains.annotations.NotNull;

public class MobileParentHolderForChildElement implements MobileParentHolder {

    private final MobileParentElement parentBlock;

    private MobileParentHolderForChildElement(MobileParentElement parentBlock) {
        this.parentBlock = parentBlock;
    }

    public static MobileParentHolderForChildElement of(@NotNull MobileParentElement parent) {
        return new MobileParentHolderForChildElement(parent);
    }

    @Override
    public @NotNull MobileElementBase getParent() {
        return parentBlock;
    }

    @Override
    public @NotNull MobileLocatorChain getLocatorChain() {
        return parentBlock.getLocatorChain();
    }

}
