package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.ROOT;

public class MappedWebBlockImpl extends WebBlockImpl {

    @Override
    public @NotNull WebLocatorChain getLocatorChainTo(@NotNull String locatorName) {
        if (ROOT.equals(locatorName)) {
            return getLocatorChain();
        }
        Optional<WebLocatorHolder> optionalLocator = locatorRegistry.getOptionalLocator(locatorName);
        if (optionalLocator.isPresent()) {
            return getLocatorChain().addLastLocator(optionalLocator.get());
        }
        return getLocatorChain();
    }

    @Override
    public @NotNull WebLocatorChain getLocatorChain() {
        return parentHolder.getLocatorChain();
    }

}
