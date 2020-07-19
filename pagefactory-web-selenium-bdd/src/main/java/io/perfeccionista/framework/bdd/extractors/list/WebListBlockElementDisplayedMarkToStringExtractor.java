package io.perfeccionista.framework.bdd.extractors.list;

import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.extractor.list.WebListBlockElementDisplayedMarkExtractor;
import io.perfeccionista.framework.pagefactory.extractor.list.WebListBlockValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilter;

public class WebListBlockElementDisplayedMarkToStringExtractor implements WebListBlockValueExtractor<String> {

    private final String elementName;

    public WebListBlockElementDisplayedMarkToStringExtractor(String elementName) {
        this.elementName = elementName;
    }

    @Override
    public MultipleResult<String> extractValues(WebList element, WebListFilter filter) {
        return new WebListBlockElementDisplayedMarkExtractor(elementName)
                .extractValues(element, filter)
                .convert(displayedMark -> displayedMark ? "1" : "0");
    }

}

