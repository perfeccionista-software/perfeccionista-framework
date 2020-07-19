package io.perfeccionista.framework.pagefactory.extractor.textlist;

import io.perfeccionista.framework.pagefactory.elements.WebTextList;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.textlist.WebTextListFilter;

public class WebTextListBlockIndexExtractor implements WebTextListBlockValueExtractor<Integer> {

    @Override
    public MultipleResult<Integer> extractValues(WebTextList element, WebTextListFilter filter) {
        // Вся логика извлечения и фильтрации здесь

        //        LocatorChain locatorChainForFilter = element.getLocatorChain();
        //        LocatorHolder ulLocatorHolderForFilter = locatorChainForFilter.getLastLocator().calculateHash(true);
        //        String ulLocatorId = ulLocatorHolderForFilter.getId();
        //        LocatorHolder liLocatorHolderForFilter = unorderedList.getLocator(LI);
        //        locatorChainForFilter.addLocator(liLocatorHolderForFilter);
        //        operationResultForFilter.getNodeHash(ulLocatorId)
        //        .orElseThrow(() -> new NodeHashNotCalculatedException(REQUESTED_NODE_HASH_NOT_CALCULATED.getMessage()));
        return null;
    }

}
