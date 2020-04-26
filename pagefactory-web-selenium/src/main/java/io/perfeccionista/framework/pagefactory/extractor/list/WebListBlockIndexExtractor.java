package io.perfeccionista.framework.pagefactory.extractor.list;

import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilter;

public class WebListBlockIndexExtractor implements WebListBlockValueExtractor<Integer> {

    @Override
    public MultipleResult<Integer> extractValues(WebList element, WebListFilter filter) {
        return null;
    }

}
