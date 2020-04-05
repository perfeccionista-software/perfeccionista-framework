package io.perfeccionista.framework.pagefactory.extractor;

import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;

public interface WebTableCellValueExtractor<V> extends WebValueExtractor<WebTable, V> {

    WebTableCellValueExtractor<V> setHash(String hash);

    SingleResult<V> extractSingleHeaderValue(WebTable element);

    SingleResult<V> extractSingleFooterValue(WebTable element);


    // Create locator chain instance for extracting with hash check
//    LocatorHolder liLocatorHolderForExtracting = getLocator(TBODY_ROW).setIndexes(result.getItems().keySet());
//    LocatorChain locatorChainForExtracting = getLocatorChain();
//            locatorChainForExtracting.getLastLocator().checkHash(result.getElementHash());
//            locatorChainForExtracting.addLocator(liLocatorHolderForExtracting);

}
