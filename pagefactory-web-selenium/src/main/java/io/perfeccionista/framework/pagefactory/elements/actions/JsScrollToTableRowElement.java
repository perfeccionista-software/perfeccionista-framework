package io.perfeccionista.framework.pagefactory.elements.actions;

import io.perfeccionista.framework.pagefactory.elements.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableFilter;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableFilterResult;
import io.perfeccionista.framework.pagefactory.jsfunction.ScrollTo;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import org.junit.platform.commons.util.ReflectionUtils;

import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.TBODY_ROW;
import static io.perfeccionista.framework.pagefactory.extractor.WebExtractors.rowIndex;

public class JsScrollToTableRowElement implements WebElementActionImplementation<Void> {

    @Override
    public Void execute(WebChildElement element, Object... args) {
        WebTable table = (WebTable) element;
        WebTableFilter filter = (WebTableFilter) args[0];
        WebTableFilterResult tableFilterResult = filter.filter(table);
        SingleResult<Integer> result = tableFilterResult
                .extractOneRow(rowIndex());

        // Create locator chain instance for scrolling with hash check
        WebLocatorHolder liLocatorHolderForScroll = table.getLocator(TBODY_ROW)
                .setSingle(true)
                .setIndex(result.getIndex());
        WebLocatorChain locatorChainForScroll = element.getLocatorChain()
                .addExpectedHashToLastLocator(tableFilterResult.getHash())
                .addLocator(liLocatorHolderForScroll);

        // Create and execute scroll operation
        ScrollTo scrollToFunction = ReflectionUtils.newInstance(ScrollTo.class);
        JsOperation<Void> operation = JsOperation.of(locatorChainForScroll, scrollToFunction);
        return element.getWebBrowserDispatcher().executor()
                .executeOperation(operation)
                .singleResult().get();
    }

}