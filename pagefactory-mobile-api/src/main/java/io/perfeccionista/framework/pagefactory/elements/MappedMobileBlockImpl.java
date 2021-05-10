package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.elements.locators.MobileLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.MobileLocatorHolder;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.ROOT;

public class MappedMobileBlockImpl extends MobileBlockImpl {

    @Override
    public @NotNull MobileLocatorChain getLocatorChainTo(@NotNull String locatorName) {
        if (ROOT.equals(locatorName)) {
            return getLocatorChain();
        }
        Optional<MobileLocatorHolder> optionalLocator = locatorRegistry.getOptionalLocator(locatorName);
        if (optionalLocator.isPresent()) {
            return getLocatorChain().addLastLocator(optionalLocator.get());
        }
        return getLocatorChain();
    }

    @Override
    public @NotNull MobileLocatorChain getLocatorChain() {
        return parentHolder.getLocatorChain();
    }

}

