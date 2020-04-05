package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.pagefactory.elements.locators.LocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.LocatorHolder;
import io.perfeccionista.framework.pagefactory.elements.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.WebStringTable;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;
import io.perfeccionista.framework.pagefactory.filter.WebStringTableFilter;
import io.perfeccionista.framework.pagefactory.js.ScrollTo;
import io.perfeccionista.framework.pagefactory.operations.JsOperation;
import org.junit.platform.commons.util.ReflectionUtils;

import static io.perfeccionista.framework.pagefactory.elements.locators.Components.TBODY_ROW;

public class JsScrollToStringTableRowElement implements WebElementMethodImplementation<Void> {

    @Override
    public Void execute(WebChildElement element, Object... args) {
        WebStringTable table = (WebStringTable) element;
        WebStringTableFilter filter = (WebStringTableFilter) args[0];
        SingleResult<Integer> result = filter.singleResult(table);

        // Create locator chain instance for scrolling with hash check
        LocatorHolder liLocatorHolderForScroll = table.getLocator(TBODY_ROW).setSingle(true).setIndexes(result.getIndex());
        LocatorChain locatorChainForScroll = element.getLocatorChain();
        locatorChainForScroll.getLastLocator().checkHash(result.getElementHash());
        locatorChainForScroll.addLocator(liLocatorHolderForScroll);

        // Create and execute scroll operation
        ScrollTo scrollToFunction = ReflectionUtils.newInstance(ScrollTo.class);
        JsOperation<SingleResult<Void>> operation = JsOperation.single(locatorChainForScroll, scrollToFunction);
        return element.getDriverInstance().getDriverOperationExecutor().executeOperation(operation).get();
    }

}
