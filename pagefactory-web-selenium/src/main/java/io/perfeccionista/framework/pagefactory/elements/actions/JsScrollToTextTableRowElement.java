package io.perfeccionista.framework.pagefactory.elements.actions;

import io.perfeccionista.framework.pagefactory.elements.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.WebTextTable;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import io.perfeccionista.framework.pagefactory.extractor.texttable.WebTextTableRowIndexExtractor;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;
import io.perfeccionista.framework.pagefactory.filter.texttable.WebTextTableFilter;
import io.perfeccionista.framework.pagefactory.filter.texttable.WebTextTableFilterResult;
import io.perfeccionista.framework.pagefactory.jsfunction.ScrollTo;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import org.junit.platform.commons.util.ReflectionUtils;

import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.TBODY_ROW;

public class JsScrollToTextTableRowElement implements WebElementActionImplementation<Void> {

    @Override
    public Void execute(WebChildElement element, Object... args) {
        WebTextTable table = (WebTextTable) element;
        WebTextTableFilter filter = (WebTextTableFilter) args[0];
        WebTextTableFilterResult textTableFilterResult = filter.filter(table);
        SingleResult<Integer> foundIndex = textTableFilterResult
                .extractOneRow(new WebTextTableRowIndexExtractor());

        // Create locator chain instance for scrolling with hash check
        WebLocatorHolder liLocatorHolderForScroll = table.getLocator(TBODY_ROW)
                .setSingle(true)
                .setIndex(foundIndex.getIndex());
        WebLocatorChain locatorChainForScroll = table.getLocatorChain()
                .addExpectedHashToLastLocator(textTableFilterResult.getHash())
                .addLocator(liLocatorHolderForScroll);

        // Create and execute scroll operation
        ScrollTo scrollToFunction = ReflectionUtils.newInstance(ScrollTo.class);
        JsOperation<Void> operation = JsOperation.of(locatorChainForScroll, scrollToFunction);
        return element.getWebBrowserDispatcher().executor()
                .executeOperation(operation)
                .singleResult().get();
    }

}
