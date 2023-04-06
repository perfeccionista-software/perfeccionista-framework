package io.perfeccionista.framework.pagefactory.elements.impl;

import io.perfeccionista.framework.pagefactory.elements.selectors.WebSelectorChain;
import io.perfeccionista.framework.pagefactory.elements.selectors.WebSelectorHolder;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.ROOT;

public class MappedWebBlockImpl extends WebBlockImpl {

    @Override
    public @NotNull WebSelectorChain getSelectorChainTo(@NotNull String componentName) {
        if (ROOT.equals(componentName)) {
            return this.getSelectorChain();
        }
        Optional<WebSelectorHolder> optionalLocator = selectorRegistry.getOptionalSelector(componentName);
        if (optionalLocator.isPresent()) {
            return this.getSelectorChain().addLastSelector(optionalLocator.get());
        }
        return this.getSelectorChain();
    }

    @Override
    public @NotNull WebSelectorChain getSelectorChain() {
        return parentHolder.getSelectorChain();
    }

}
