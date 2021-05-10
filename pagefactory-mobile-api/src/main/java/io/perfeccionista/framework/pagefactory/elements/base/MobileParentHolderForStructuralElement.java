package io.perfeccionista.framework.pagefactory.elements.base;

import io.perfeccionista.framework.pagefactory.elements.locators.MobileLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.MobileLocatorHolder;
import org.jetbrains.annotations.NotNull;

import java.util.Deque;

public class MobileParentHolderForStructuralElement implements MobileParentHolder {

    private final MobileChildElement parentElement;
    private final String parentElementHash;
    private final Deque<MobileLocatorHolder> dynamicParentLocators;      // Промежуточные динамические локаторы с индексами, например, локаторы блоков списка или строк таблицы

    private MobileParentHolderForStructuralElement(MobileChildElement parentElement,
                                                   String parentElementHash,
                                                   Deque<MobileLocatorHolder> dynamicParentLocators) {
        this.parentElement = parentElement;
        this.parentElementHash = parentElementHash;
        this.dynamicParentLocators = dynamicParentLocators;
    }

    public static MobileParentHolderForStructuralElement of(@NotNull MobileChildElement parent,
                                                            @NotNull String parentElementHash,
                                                            @NotNull Deque<MobileLocatorHolder> dynamicParentLocators) {
        return new MobileParentHolderForStructuralElement(parent, parentElementHash, dynamicParentLocators);
    }

    public static MobileParentHolderForStructuralElement of(@NotNull MobileChildElement parent,
                                                            @NotNull Deque<MobileLocatorHolder> dynamicParentLocators) {
        return new MobileParentHolderForStructuralElement(parent, null, dynamicParentLocators);
    }

    @Override
    public @NotNull MobileElementBase getParent() {
        return parentElement;
    }

    @Override
    public @NotNull MobileLocatorChain getLocatorChain() {
        MobileLocatorChain locatorChain = parentElement.getLocatorChain();
        locatorChain.getLastLocator().setExpectedHash(parentElementHash);
        locatorChain.addLastLocators(dynamicParentLocators);
        return locatorChain;
    }

}
