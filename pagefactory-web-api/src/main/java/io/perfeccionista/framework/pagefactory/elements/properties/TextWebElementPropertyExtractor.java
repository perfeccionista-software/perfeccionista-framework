package io.perfeccionista.framework.pagefactory.elements.properties;

import io.perfeccionista.framework.exceptions.ElementCast;
import io.perfeccionista.framework.exceptions.StateExtractorCreating;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElementBase;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import io.perfeccionista.framework.pagefactory.elements.methods.WebGetTextAvailable;
import io.perfeccionista.framework.pagefactory.elements.properties.base.WebElementPropertyExtractor;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperation;
import io.perfeccionista.framework.pagefactory.operation.handler.JsGetText;
import io.perfeccionista.framework.pagefactory.operation.type.WebCustomOperationType;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.ELEMENT_CANNOT_BE_CASTED;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.STATE_EXTRACTOR_WRONG_ARGUMENT_NUMBER;

public class TextWebElementPropertyExtractor implements WebElementPropertyExtractor {

    public TextWebElementPropertyExtractor(@NotNull List<String> args) {
        int size = args.size();
        if (size != 0) {
            throw StateExtractorCreating.exception(STATE_EXTRACTOR_WRONG_ARGUMENT_NUMBER.getMessage(2, size));
        }
    }

    @Override
    public WebElementOperation<String> getOperation(@NotNull WebChildElementBase element, Optional<WebLocatorHolder> locatorHolder) {
        WebLocatorChain locatorChain = element.getLocatorChain();
        locatorHolder.ifPresent(locatorChain::addLastLocator);
        if (element instanceof WebGetTextAvailable) {
            return WebElementOperation.of(locatorChain, WebCustomOperationType.of(new JsGetText()));
        }
        throw ElementCast.exception(ELEMENT_CANNOT_BE_CASTED.getMessage(element.getClass().getCanonicalName(), WebGetTextAvailable.class.getCanonicalName()));
    }

}
