package io.perfeccionista.framework.pagefactory.elements.states;

import io.perfeccionista.framework.exceptions.StateExtractorCreating;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElementBase;
import io.perfeccionista.framework.pagefactory.elements.locators.MobileLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.MobileLocatorHolder;
import io.perfeccionista.framework.pagefactory.elements.states.base.MobileElementStateExtractor;
import io.perfeccionista.framework.pagefactory.operation.MobileElementOperation;
import io.perfeccionista.framework.pagefactory.operation.type.MobileCheckBooleanAttributeValueOperationType;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.STATE_EXTRACTOR_WRONG_ARGUMENT_NUMBER;

public class CheckBooleanAttributeMobileElementStateExtractor implements MobileElementStateExtractor {

    private final String attributeName;
    private final boolean attributeValue;

    public CheckBooleanAttributeMobileElementStateExtractor(@NotNull List<String> args) {
        int size = args.size();
        if (size != 2) {
            throw StateExtractorCreating.exception(STATE_EXTRACTOR_WRONG_ARGUMENT_NUMBER.getMessage(2, size));
        }
        this.attributeName = args.get(0);
        // TODO: Сделать проверку типа с корректной ошибкой
        this.attributeValue = Boolean.parseBoolean(args.get(1));
    }

    @Override
    public MobileElementOperation<Boolean> getOperation(@NotNull MobileChildElementBase element, Optional<MobileLocatorHolder> locatorHolder) {
        MobileLocatorChain locatorChain = element.getLocatorChain();
        locatorHolder.ifPresent(locatorChain::addLastLocator);
        return MobileElementOperation.of(locatorChain, MobileCheckBooleanAttributeValueOperationType.of(element, attributeName, attributeValue));
    }

}
