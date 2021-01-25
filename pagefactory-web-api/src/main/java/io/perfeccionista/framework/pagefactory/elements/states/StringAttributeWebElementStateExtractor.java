package io.perfeccionista.framework.pagefactory.elements.states;

import io.perfeccionista.framework.exceptions.StateExtractorCreating;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElementBase;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import io.perfeccionista.framework.pagefactory.elements.states.base.WebElementStateExtractor;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperation;
import io.perfeccionista.framework.pagefactory.operation.type.WebCheckStringAttributeValueOperationType;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.STATE_EXTRACTOR_WRONG_ARGUMENT_NUMBER;
import static io.perfeccionista.framework.value.Values.stringEquals;

public class StringAttributeWebElementStateExtractor implements WebElementStateExtractor {

    private final String attributeName;
    private final String attributeValue;

    public StringAttributeWebElementStateExtractor(@NotNull List<String> args) {
        int size = args.size();
        if (size != 2) {
            throw StateExtractorCreating.exception(STATE_EXTRACTOR_WRONG_ARGUMENT_NUMBER.getMessage(2, size));
        }
        this.attributeName = args.get(0);
        this.attributeValue = args.get(1);
    }

    @Override
    public WebElementOperation<Boolean> getOperation(@NotNull WebChildElementBase element, Optional<WebLocatorHolder> locatorHolder) {
        WebLocatorChain locatorChain = element.getLocatorChain();
        locatorHolder.ifPresent(locator -> locatorChain.addLastLocator(locator.setStrictSearch(false)));
        return WebElementOperation.of(locatorChain, WebCheckStringAttributeValueOperationType.of(element, attributeName, stringEquals(attributeValue)));
    }

}
