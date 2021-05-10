package io.perfeccionista.framework.pagefactory.elements.base;

import io.perfeccionista.framework.pagefactory.elements.locators.MobileLocatorChain;
import org.jetbrains.annotations.NotNull;

public interface MobileLocatorChainAvailable {

    @NotNull MobileLocatorChain getLocatorChainTo(@NotNull String locatorName);

    @NotNull MobileLocatorChain getLocatorChain();

}
