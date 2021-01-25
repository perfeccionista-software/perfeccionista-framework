package io.perfeccionista.framework.pagefactory.elements.base;

import io.perfeccionista.framework.pagefactory.elements.locators.MobileLocatorChain;
import org.jetbrains.annotations.NotNull;

public class MobileParentHolderForIsolatedStructuralElement implements MobileParentHolder {

    private final MobileChildElement parentElement;

    private MobileParentHolderForIsolatedStructuralElement(MobileChildElement parentElement) {
        this.parentElement = parentElement;
    }

    public static MobileParentHolderForIsolatedStructuralElement of(@NotNull MobileChildElement parent) {
        return new MobileParentHolderForIsolatedStructuralElement(parent);
    }

    @Override
    public @NotNull MobileElementBase getParent() {
        return parentElement;
    }

    @Override
    public @NotNull MobileLocatorChain getLocatorChain() {
        return MobileLocatorChain.empty();
    }

}
