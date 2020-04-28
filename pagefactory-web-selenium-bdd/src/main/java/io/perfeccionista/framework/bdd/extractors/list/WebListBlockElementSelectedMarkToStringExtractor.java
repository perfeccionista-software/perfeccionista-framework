package io.perfeccionista.framework.bdd.extractors.list;

import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.extractor.list.WebListBlockElementSelectedMarkExtractor;
import io.perfeccionista.framework.pagefactory.extractor.list.WebListBlockValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilter;

public class WebListBlockElementSelectedMarkToStringExtractor implements WebListBlockValueExtractor<String> {

    private final String elementName;

    public WebListBlockElementSelectedMarkToStringExtractor(String elementName) {
        this.elementName = elementName;
    }

    @Override
    public MultipleResult<String> extractValues(WebList element, WebListFilter filter) {
        return new WebListBlockElementSelectedMarkExtractor(elementName)
                .extractValues(element, filter)
                .convert(selectedMark -> selectedMark ? "1" : "0");
    }

}
