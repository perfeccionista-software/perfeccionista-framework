package io.perfeccionista.framework.pagefactory.elements.base;

import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import org.jetbrains.annotations.NotNull;

public interface WebLocatorChainAvailable {

    @NotNull WebLocatorChain getLocatorChainTo(@NotNull String locatorName);

    @NotNull WebLocatorChain getLocatorChain();

}
