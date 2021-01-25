package io.perfeccionista.framework.pagefactory.elements.properties;

import io.perfeccionista.framework.exceptions.StateExtractorCreating;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElementBase;
import io.perfeccionista.framework.pagefactory.elements.locators.MobileLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.MobileLocatorHolder;
import io.perfeccionista.framework.pagefactory.elements.properties.base.MobileElementPropertyExtractor;
import io.perfeccionista.framework.pagefactory.operation.MobileElementOperation;
import io.perfeccionista.framework.pagefactory.operation.type.MobileGetStringAttributeValueOperationType;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.STATE_EXTRACTOR_WRONG_ARGUMENT_NUMBER;

public class StringAttributeMobileElementPropertyExtractor implements MobileElementPropertyExtractor {

    private final String attributeName;

    public StringAttributeMobileElementPropertyExtractor(@NotNull List<String> args) {
        int size = args.size();
        if (size != 1) {
            throw StateExtractorCreating.exception(STATE_EXTRACTOR_WRONG_ARGUMENT_NUMBER.getMessage(2, size));
        }
        this.attributeName = args.get(0);
    }

    @Override
    public MobileElementOperation<String> getOperation(@NotNull MobileChildElementBase element, Optional<MobileLocatorHolder> locatorHolder) {
        MobileLocatorChain locatorChain = element.getLocatorChain();
        locatorHolder.ifPresent(locatorChain::addLastLocator);
        return MobileElementOperation.of(locatorChain, MobileGetStringAttributeValueOperationType.of(element, attributeName));
    }

}
