package io.perfeccionista.framework.pagefactory.elements.base;

import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import org.jetbrains.annotations.NotNull;

public class WebParentHolderForIsolatedStructuralElement implements WebParentHolder {

    private final WebChildElement parentElement;

    private WebParentHolderForIsolatedStructuralElement(WebChildElement parentElement) {
        this.parentElement = parentElement;
    }

    public static WebParentHolderForIsolatedStructuralElement of(@NotNull WebChildElement parent) {
        return new WebParentHolderForIsolatedStructuralElement(parent);
    }

    @Override
    public @NotNull WebElementBase getParent() {
        return parentElement;
    }

    @Override
    public @NotNull WebLocatorChain getLocatorChain() {
        return WebLocatorChain.empty();
    }

}