package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.pagefactory.elements.locators.LocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.LocatorHolder;
import io.perfeccionista.framework.pagefactory.elements.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.WebSimpleTable;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;
import io.perfeccionista.framework.pagefactory.filter.JsStringTableRowFilter;
import io.perfeccionista.framework.pagefactory.js.ScrollTo;
import io.perfeccionista.framework.pagefactory.operations.JsOperation;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;
import org.junit.platform.commons.util.ReflectionUtils;

import static io.perfeccionista.framework.pagefactory.elements.locators.Components.TBODY_ROW;

public class JsScrollToStringTableRowElement implements WebElementMethodImplementation<Void> {

    @Override
    public OperationResult<Void> execute(WebChildElement element, Object... args) {
        WebSimpleTable table = (WebSimpleTable) element;
        JsStringTableRowFilter filter = (JsStringTableRowFilter) args[0];
        return OperationResult.of(() -> {
            SingleResult<Integer> result = filter.singleResult(table);

            // Create locator chain instance for scrolling with hash check
            LocatorHolder liLocatorHolderForScroll = table.getLocator(TBODY_ROW).setSingle(true).setIndexes(result.getItemIndex());
            LocatorChain locatorChainForScroll = element.getLocatorChain();
            locatorChainForScroll.getLastLocator().checkHash(result.getElementHash());
            locatorChainForScroll.addLocator(liLocatorHolderForScroll);

            // Create and execute scroll operation
            ScrollTo scrollToFunction = ReflectionUtils.newInstance(ScrollTo.class);
            JsOperation<SingleResult<Void>> operation = JsOperation.single(locatorChainForScroll, scrollToFunction);
            return element.getDriverInstance().getDriverOperationExecutor().executeOperation(operation).getItem();
        });
    }

}
