package io.perfeccionista.framework.pagefactory.elements.properties;

import io.perfeccionista.framework.exceptions.StateExtractorCreating;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElementBase;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import io.perfeccionista.framework.pagefactory.elements.properties.base.WebElementPropertyExtractor;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperation;
import io.perfeccionista.framework.pagefactory.operation.type.WebGetStringAttributeValueOperationType;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.STATE_EXTRACTOR_WRONG_ARGUMENT_NUMBER;
import static io.perfeccionista.framework.value.Values.stringEquals;

public class StringAttributeWebElementPropertyExtractor implements WebElementPropertyExtractor {

    private final String attributeName;

    public StringAttributeWebElementPropertyExtractor(@NotNull List<String> args) {
        int size = args.size();
        if (size != 1) {
            throw StateExtractorCreating.exception(STATE_EXTRACTOR_WRONG_ARGUMENT_NUMBER.getMessage(2, size));
        }
        this.attributeName = args.get(0);
    }

    @Override
    public WebElementOperation<String> getOperation(@NotNull WebChildElementBase element, Optional<WebLocatorHolder> locatorHolder) {
        WebLocatorChain locatorChain = element.getLocatorChain();
        locatorHolder.ifPresent(locatorChain::addLastLocator);
        return WebElementOperation.of(locatorChain, WebGetStringAttributeValueOperationType.of(element, attributeName));
    }

}
