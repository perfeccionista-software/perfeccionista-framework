package io.perfeccionista.framework.pagefactory.itemextractor;

import io.perfeccionista.framework.pagefactory.elements.locators.LocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.LocatorHolder;
import io.perfeccionista.framework.pagefactory.elements.web.WebTable;
import io.perfeccionista.framework.pagefactory.itemfilter.SingleResult;

import static io.perfeccionista.framework.pagefactory.elements.locators.Components.TBODY_ROW;

public interface JsWebTableRowValueExtractor<V> extends WebValueExtractor<WebTable, V> {

    JsWebTableRowValueExtractor<V> setHash(String hash);

    SingleResult<V> extractSingleHeaderValue(WebTable element);

    SingleResult<V> extractSingleFooterValue(WebTable element);


    // Create locator chain instance for extracting with hash check
//    LocatorHolder liLocatorHolderForExtracting = getLocator(TBODY_ROW).setIndexes(result.getItems().keySet());
//    LocatorChain locatorChainForExtracting = getLocatorChain();
//            locatorChainForExtracting.getLastLocator().checkHash(result.getElementHash());
//            locatorChainForExtracting.addLocator(liLocatorHolderForExtracting);

}
