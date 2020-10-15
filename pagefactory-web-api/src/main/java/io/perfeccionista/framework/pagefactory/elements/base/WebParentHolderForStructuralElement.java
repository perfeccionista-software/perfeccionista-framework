package io.perfeccionista.framework.pagefactory.elements.base;

import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import org.jetbrains.annotations.NotNull;

import java.util.Deque;

public class WebParentHolderForStructuralElement implements WebParentHolder {

    private final WebChildElement parentElement;
    private final String parentElementHash;
    private final Deque<WebLocatorHolder> dynamicParentLocators;      // Промежуточные динамические локаторы с индексами, например, локаторы блоков списка или строк таблицы

    private WebParentHolderForStructuralElement(WebChildElement parentElement,
                                                String parentElementHash,
                                                Deque<WebLocatorHolder> dynamicParentLocators) {
        this.parentElement = parentElement;
        this.parentElementHash = parentElementHash;
        this.dynamicParentLocators = dynamicParentLocators;
    }

    public static WebParentHolderForStructuralElement of(@NotNull WebChildElement parent,
                                                         @NotNull String parentElementHash,
                                                         @NotNull Deque<WebLocatorHolder> dynamicParentLocators) {
        return new WebParentHolderForStructuralElement(parent, parentElementHash, dynamicParentLocators);
    }

    public static WebParentHolderForStructuralElement of(@NotNull WebChildElement parent,
                                                         @NotNull Deque<WebLocatorHolder> dynamicParentLocators) {
        return new WebParentHolderForStructuralElement(parent, null, dynamicParentLocators);
    }

    @Override
    public @NotNull WebElementBase getParent() {
        return parentElement;
    }

    @Override
    public @NotNull WebLocatorChain getLocatorChain() {
        WebLocatorChain locatorChain = parentElement.getLocatorChain();
        locatorChain.getLastLocator().setExpectedHash(parentElementHash);
        locatorChain.addLastLocators(dynamicParentLocators);
        return locatorChain;
    }

}
