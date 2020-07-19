package io.perfeccionista.framework.pagefactory.elements.actions;

import io.perfeccionista.framework.attachment.JsonAttachmentEntry;
import io.perfeccionista.framework.exceptions.LocatorNotDeclaredException;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.WebTextList;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import io.perfeccionista.framework.pagefactory.extractor.textlist.WebTextListBlockIndexExtractor;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;
import io.perfeccionista.framework.pagefactory.filter.textlist.WebTextListFilter;
import io.perfeccionista.framework.pagefactory.filter.textlist.WebTextListFilterResult;
import io.perfeccionista.framework.pagefactory.jsfunction.ScrollTo;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import io.perfeccionista.framework.pagefactory.operation.JsOperationResult;

import java.util.Optional;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryMessages.ELEMENT_LOCATOR_NOT_DECLARED;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.LI;

public class JsScrollToTextBlockElement implements WebElementActionImplementation<Void> {

    @Override
    public Void execute(WebChildElement element, Object... args) {
        WebTextList unorderedList = (WebTextList) element;
        WebTextListFilter filter = (WebTextListFilter) args[0];
        WebTextListFilterResult textListFilterResult = filter.filter(unorderedList);
        SingleResult<Integer> result = textListFilterResult
                .extractOne(new WebTextListBlockIndexExtractor());

        // Create locator chain instance for scrolling with hash check
        WebLocatorHolder liLocatorHolderForScroll = unorderedList.getLocator(LI)
                .orElseThrow(() -> new LocatorNotDeclaredException(ELEMENT_LOCATOR_NOT_DECLARED.getMessage(LI)))
                .setSingle(true)
                .setIndex(result.getIndex());
        WebLocatorChain locatorChainForScroll = element.getLocatorChain()
                .addExpectedHashToLastLocator(textListFilterResult.getHash())
                .addLocator(liLocatorHolderForScroll);

        // Create and execute scroll operation
        ScrollTo scrollToFunction = new ScrollTo();
        JsOperation<Void> operation = JsOperation.of(locatorChainForScroll, scrollToFunction);
        JsOperationResult<Void> operationResult = element.getWebBrowserDispatcher().executor().executeOperation(operation);
        operationResult.ifException(exception -> {
            throw exception.addAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()));
        });
        return operationResult.singleResult().get();
    }

    @Override
    public Optional<JsOperation<Void>> getJsOperation(WebChildElement element, Object... args) {
        WebTextList unorderedList = (WebTextList) element;
        WebTextListFilter filter = (WebTextListFilter) args[0];
        WebTextListFilterResult textListFilterResult = filter.filter(unorderedList);
        SingleResult<Integer> result = textListFilterResult
                .extractOne(new WebTextListBlockIndexExtractor());

        // Create locator chain instance for scrolling with hash check
        WebLocatorHolder liLocatorHolderForScroll = unorderedList.getLocator(LI)
                .orElseThrow(() -> new LocatorNotDeclaredException(ELEMENT_LOCATOR_NOT_DECLARED.getMessage(LI)))
                .setSingle(true)
                .setIndex(result.getIndex());
        WebLocatorChain locatorChainForScroll = element.getLocatorChain()
                .addExpectedHashToLastLocator(textListFilterResult.getHash())
                .addLocator(liLocatorHolderForScroll);

        // Create and execute scroll operation
        ScrollTo scrollToFunction = new ScrollTo();
        return Optional.of(JsOperation.of(locatorChainForScroll, scrollToFunction));
    }

}
