package io.perfeccionista.framework.pagefactory.elements.base;

import io.perfeccionista.framework.pagefactory.elements.selectors.WebSelectorChain;
import org.jetbrains.annotations.NotNull;

public interface WebSelectorChainAvailable {

    @NotNull WebSelectorChain getSelectorChainTo(@NotNull String componentName);

    @NotNull WebSelectorChain getSelectorChain();

}
