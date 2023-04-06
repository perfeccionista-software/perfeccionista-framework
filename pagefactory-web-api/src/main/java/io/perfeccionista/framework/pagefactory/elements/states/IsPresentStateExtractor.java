package io.perfeccionista.framework.pagefactory.elements.states;

import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.selectors.WebSelectorChain;
import io.perfeccionista.framework.pagefactory.elements.selectors.WebSelectorHolder;
import io.perfeccionista.framework.pagefactory.elements.states.base.WebElementStateExtractor;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperation;
import io.perfeccionista.framework.pagefactory.operation.type.WebGetIsPresentOperationType;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.PRESENTED;

// TODO: Разобраться с формированием операции и компонента, который в нее передается
public class IsPresentStateExtractor implements WebElementStateExtractor {

    @Override
    public WebElementOperation<Boolean> getOperation(@NotNull WebChildElement element, Optional<WebSelectorHolder> locatorHolder) {
        WebSelectorChain locatorChain = element.getSelectorChain();
        locatorHolder.ifPresent(locator -> locatorChain.addLastSelector(locator.setStrictSearch(false)));
        return WebElementOperation.of(locatorChain, WebGetIsPresentOperationType.of(element, PRESENTED));
    }

}
