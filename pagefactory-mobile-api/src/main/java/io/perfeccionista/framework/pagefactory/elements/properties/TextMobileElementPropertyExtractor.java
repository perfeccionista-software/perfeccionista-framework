package io.perfeccionista.framework.pagefactory.elements.properties;

import io.perfeccionista.framework.exceptions.ElementCast;
import io.perfeccionista.framework.exceptions.StateExtractorCreating;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElementBase;
import io.perfeccionista.framework.pagefactory.elements.locators.MobileLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.MobileLocatorHolder;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileGetTextAvailable;
import io.perfeccionista.framework.pagefactory.elements.properties.base.MobileElementPropertyExtractor;
import io.perfeccionista.framework.pagefactory.operation.MobileElementOperation;
import io.perfeccionista.framework.pagefactory.operation.type.MobileGetTextOperationType;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.ELEMENT_CANNOT_BE_CASTED;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.STATE_EXTRACTOR_WRONG_ARGUMENT_NUMBER;

public class TextMobileElementPropertyExtractor implements MobileElementPropertyExtractor {

    public TextMobileElementPropertyExtractor(@NotNull List<String> args) {
        int size = args.size();
        if (size != 0) {
            throw StateExtractorCreating.exception(STATE_EXTRACTOR_WRONG_ARGUMENT_NUMBER.getMessage(2, size));
        }
    }

    @Override
    public MobileElementOperation<String> getOperation(@NotNull MobileChildElementBase element, Optional<MobileLocatorHolder> locatorHolder) {
        MobileLocatorChain locatorChain = element.getLocatorChain();
        locatorHolder.ifPresent(locatorChain::addLastLocator);
        if (element instanceof MobileGetTextAvailable) {
            return MobileElementOperation.of(locatorChain, MobileGetTextOperationType.of((MobileGetTextAvailable) element));
        }
        throw ElementCast.exception(ELEMENT_CANNOT_BE_CASTED.getMessage(element.getClass().getCanonicalName(), MobileGetTextAvailable.class.getCanonicalName()));
    }

}
