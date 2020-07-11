package io.perfeccionista.framework.pagefactory.elements.actions;

import io.perfeccionista.framework.attachment.JsonAttachmentEntry;
import io.perfeccionista.framework.exceptions.LocatorNotDeclaredException;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilter;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilterResult;
import io.perfeccionista.framework.pagefactory.jsfunction.GetWebElement;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import io.perfeccionista.framework.pagefactory.operation.JsOperationResult;
import org.openqa.selenium.WebElement;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryMessages.ELEMENT_LOCATOR_NOT_DECLARED;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.LI;
import static io.perfeccionista.framework.pagefactory.extractor.WebExtractors.blockIndex;

public class SeleniumClickToBlockElement implements WebElementActionImplementation<Void> {

    @Override
    public Void execute(WebChildElement element, Object... args) {
        WebList unorderedList = (WebList) element;
        WebListFilter filter = (WebListFilter) args[0];
        WebListFilterResult listFilterResult = filter.filter(unorderedList);
        SingleResult<Integer> result = listFilterResult
                .extractOne(blockIndex());

        // Create locator chain instance for scrolling with hash check
        WebLocatorHolder liLocatorHolderForScroll = unorderedList.getLocator(LI)
                .orElseThrow(() -> new LocatorNotDeclaredException(ELEMENT_LOCATOR_NOT_DECLARED.getMessage(LI)))
                .setSingle(true)
                .setIndex(result.getIndex());
        WebLocatorChain locatorChainForScroll = element.getLocatorChain()
                .addExpectedHashToLastLocator(listFilterResult.getHash())
                .addLocator(liLocatorHolderForScroll);

        // Create and execute scroll operation
        GetWebElement getWebElementFunction = new GetWebElement();
        JsOperation<WebElement> operation = JsOperation.of(locatorChainForScroll, getWebElementFunction);
        JsOperationResult<WebElement> operationResult = element.getWebBrowserDispatcher().executor().executeOperation(operation);
        operationResult.ifException(exception -> {
            throw exception.addAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()));
        });
        WebElement elementToClick = operationResult.singleResult().get();
        element.getWebBrowserDispatcher().getExceptionMapper()
                .map(elementToClick::click, element.getElementIdentifier().getLastUsedName())
                .ifException(exception -> {
                    throw exception.addAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()));
                });
        return null;
    }

}

