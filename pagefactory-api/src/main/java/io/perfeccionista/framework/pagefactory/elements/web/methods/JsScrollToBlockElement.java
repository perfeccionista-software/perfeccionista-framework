package io.perfeccionista.framework.pagefactory.elements.web.methods;

import io.perfeccionista.framework.pagefactory.elements.locators.LocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.LocatorHolder;
import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethodImplementation;
import io.perfeccionista.framework.pagefactory.elements.web.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.web.WebUnorderedList;
import io.perfeccionista.framework.pagefactory.itemfilter.SingleResult;
import io.perfeccionista.framework.pagefactory.itemfilter.js.JsBlockFilter;
import io.perfeccionista.framework.pagefactory.js.ScrollTo;
import io.perfeccionista.framework.pagefactory.operations.JsOperation;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

import static io.perfeccionista.framework.pagefactory.elements.locators.Components.LI;

public class JsScrollToBlockElement implements ElementMethodImplementation<WebChildElement, Void> {

    @Override
    public OperationResult<Void> execute(WebChildElement element, Object... args) {
        WebUnorderedList unorderedList = (WebUnorderedList) element;
        JsBlockFilter filter = (JsBlockFilter) args[0];
        return OperationResult.execute(() -> {
            SingleResult<Integer> result = filter.singleResult(unorderedList);

            // Create locator chain instance for scrolling with hash check
            LocatorHolder liLocatorHolderForScroll = unorderedList.getLocator(LI).setSingle(true).setIndexes(result.getItemIndex());
            LocatorChain locatorChainForScroll = element.getLocatorChain();
            locatorChainForScroll.getLastLocator().checkHash(result.getElementHash());
            locatorChainForScroll.addLocator(liLocatorHolderForScroll);

            // Create and execute scroll operation
            JsOperation<SingleResult<Void>> operation = JsOperation.single(locatorChainForScroll, ScrollTo.class);
            return element.getDriverInstance().getDriverOperationExecutor().executeOperation(operation).getItem();
        });
    }

}
