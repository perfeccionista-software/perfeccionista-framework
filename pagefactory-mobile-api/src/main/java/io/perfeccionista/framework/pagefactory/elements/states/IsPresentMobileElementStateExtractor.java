package io.perfeccionista.framework.pagefactory.elements.states;

import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElementBase;
import io.perfeccionista.framework.pagefactory.elements.locators.MobileLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.MobileLocatorHolder;
import io.perfeccionista.framework.pagefactory.elements.states.base.MobileElementStateExtractor;
import io.perfeccionista.framework.pagefactory.operation.MobileElementOperation;
import io.perfeccionista.framework.pagefactory.operation.type.MobileGetIsPresentOperationType;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

public class IsPresentMobileElementStateExtractor implements MobileElementStateExtractor {
    public IsPresentMobileElementStateExtractor(@NotNull List<String> args) {
    }
        @Override
    public MobileElementOperation<Boolean> getOperation(@NotNull MobileChildElementBase element, Optional<MobileLocatorHolder> locatorHolder) {
            MobileLocatorChain locatorChain = element.getLocatorChain();
            locatorHolder.ifPresent(locator -> locatorChain.addLastLocator(locator.setStrictSearch(false)));
            return MobileElementOperation.of(locatorChain, MobileGetIsPresentOperationType.of(element));
    }
}
