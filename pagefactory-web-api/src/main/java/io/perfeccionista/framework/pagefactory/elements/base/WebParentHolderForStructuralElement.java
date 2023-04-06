package io.perfeccionista.framework.pagefactory.elements.base;

import io.perfeccionista.framework.pagefactory.elements.selectors.WebSelectorChain;
import io.perfeccionista.framework.pagefactory.elements.selectors.WebSelectorHolder;
import org.jetbrains.annotations.NotNull;

import java.util.Deque;

public class WebParentHolderForStructuralElement implements WebParentHolder {

    private final WebChildElement parentElement;
    private final String parentElementHash;
    private final Deque<WebSelectorHolder> dynamicParentSelectors;      // Промежуточные динамические локаторы с индексами, например, локаторы блоков списка или строк таблицы

    private WebParentHolderForStructuralElement(WebChildElement parentElement,
                                                String parentElementHash,
                                                Deque<WebSelectorHolder> dynamicParentSelectors) {
        this.parentElement = parentElement;
        this.parentElementHash = parentElementHash;
        this.dynamicParentSelectors = dynamicParentSelectors;
    }

    public static WebParentHolderForStructuralElement of(@NotNull WebChildElement parent,
                                                         @NotNull String parentElementHash,
                                                         @NotNull Deque<WebSelectorHolder> dynamicParentSelectors) {
        return new WebParentHolderForStructuralElement(parent, parentElementHash, dynamicParentSelectors);
    }

    public static WebParentHolderForStructuralElement of(@NotNull WebChildElement parent,
                                                         @NotNull Deque<WebSelectorHolder> dynamicParentSelectors) {
        return new WebParentHolderForStructuralElement(parent, null, dynamicParentSelectors);
    }

    @Override
    public @NotNull WebElementBase getParent() {
        return parentElement;
    }

    @Override
    public @NotNull WebSelectorChain getSelectorChain() {
        WebSelectorChain selectorChain = parentElement.getSelectorChain();
        selectorChain.getLastSelector().setExpectedHash(parentElementHash);
        selectorChain.addLastSelectors(dynamicParentSelectors);
        return selectorChain;
    }

}
