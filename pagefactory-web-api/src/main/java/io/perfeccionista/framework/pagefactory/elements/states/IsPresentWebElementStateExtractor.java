package io.perfeccionista.framework.pagefactory.elements.states;

import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElementBase;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import io.perfeccionista.framework.pagefactory.elements.states.base.WebElementStateExtractor;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperation;
import io.perfeccionista.framework.pagefactory.operation.type.WebGetIsPresentOperationType;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

public class IsPresentWebElementStateExtractor implements WebElementStateExtractor {

    public IsPresentWebElementStateExtractor(@NotNull List<String> args) {
    }

    @Override
    public WebElementOperation<Boolean> getOperation(@NotNull WebChildElementBase element, Optional<WebLocatorHolder> locatorHolder) {
        WebLocatorChain locatorChain = element.getLocatorChain();
        locatorHolder.ifPresent(locator -> locatorChain.addLastLocator(locator.setStrictSearch(false)));
        return WebElementOperation.of(locatorChain, WebGetIsPresentOperationType.of((WebChildElement) element));
    }

}
