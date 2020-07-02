package io.perfeccionista.framework.pagefactory.extractor.textlist;

import io.perfeccionista.framework.pagefactory.elements.WebTextList;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.textlist.WebTextListFilter;
import io.perfeccionista.framework.pagefactory.filter.textlist.WebTextListFilterResult;

// TODO: Для пустого фильтра возвращаем все значения
public class WebTextListBlockStringExtractor implements WebTextListBlockValueExtractor<String> {

    @Override
    public MultipleResult<String> extractValues(WebTextList element, WebTextListFilterResult filter) {
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
