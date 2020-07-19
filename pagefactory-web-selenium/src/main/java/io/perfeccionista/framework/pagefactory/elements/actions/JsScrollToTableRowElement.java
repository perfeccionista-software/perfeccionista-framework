package io.perfeccionista.framework.pagefactory.elements.actions;

import io.perfeccionista.framework.attachment.JsonAttachmentEntry;
import io.perfeccionista.framework.exceptions.LocatorNotDeclaredException;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableFilter;
import io.perfeccionista.framework.pagefactory.jsfunction.ScrollTo;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import io.perfeccionista.framework.pagefactory.operation.JsOperationResult;

import java.util.Optional;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryMessages.ELEMENT_LOCATOR_NOT_DECLARED;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.TBODY_ROW;
import static io.perfeccionista.framework.pagefactory.extractor.WebExtractors.rowIndex;

public class JsScrollToTableRowElement implements WebElementActionImplementation<Void> {

    @Override
    public Void execute(WebChildElement element, Object... args) {
        WebTable table = (WebTable) element;
        WebTableFilterBuilder filter = (WebTableFilterBuilder) args[0];
        WebTableFilter tableFilter = filter.build(table);
        SingleResult<Integer> result = tableFilter
                .extractOneRow(rowIndex());

        // Create locator chain instance for scrolling with hash check
        WebLocatorHolder liLocatorHolderForScroll = table.getLocator(TBODY_ROW)
                .orElseThrow(() -> new LocatorNotDeclaredException(ELEMENT_LOCATOR_NOT_DECLARED.getMessage(TBODY_ROW)))
                .setSingle(true)
                .setIndex(result.getIndex());
        WebLocatorChain locatorChainForScroll = element.getLocatorChain()
                .addExpectedHashToLastLocator(tableFilter.getResult().getHash())
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
        WebTable table = (WebTable) element;
        WebTableFilterBuilder filter = (WebTableFilterBuilder) args[0];
        WebTableFilter tableFilter = filter.build(table);
        SingleResult<Integer> result = tableFilter
                .extractOneRow(rowIndex());

        // Create locator chain instance for scrolling with hash check
        WebLocatorHolder liLocatorHolderForScroll = table.getLocator(TBODY_ROW)
                .orElseThrow(() -> new LocatorNotDeclaredException(ELEMENT_LOCATOR_NOT_DECLARED.getMessage(TBODY_ROW)))
                .setSingle(true)
                .setIndex(result.getIndex());
        WebLocatorChain locatorChainForScroll = element.getLocatorChain()
                .addExpectedHashToLastLocator(tableFilter.getResult().getHash())
                .addLocator(liLocatorHolderForScroll);

        // Create and execute scroll operation
        ScrollTo scrollToFunction = new ScrollTo();
        return Optional.of(JsOperation.of(locatorChainForScroll, scrollToFunction));
    }

}