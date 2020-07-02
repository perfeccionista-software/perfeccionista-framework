package io.perfeccionista.framework.pagefactory.extractor.list;

import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.WebConditionProcessingResult;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilter;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilterResult;

public class WebListBlockIndexExtractor implements WebListBlockValueExtractor<Integer> {

    @Override
    public MultipleResult<Integer> extractValues(WebList element, WebListFilterResult result) {
        return null;
    }

}
