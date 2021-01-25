package io.perfeccionista.framework.pagefactory.elements.base;

import io.perfeccionista.framework.pagefactory.elements.locators.MobileLocatorChain;
import org.jetbrains.annotations.NotNull;

public interface MobileParentHolder {

    @NotNull MobileElementBase getParent();

    @NotNull MobileLocatorChain getLocatorChain();

}
