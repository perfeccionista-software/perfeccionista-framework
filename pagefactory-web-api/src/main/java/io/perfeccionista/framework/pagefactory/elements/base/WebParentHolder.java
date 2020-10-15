package io.perfeccionista.framework.pagefactory.elements.base;

import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import org.jetbrains.annotations.NotNull;

public interface WebParentHolder {

    @NotNull WebElementBase getParent();

    @NotNull WebLocatorChain getLocatorChain();

}