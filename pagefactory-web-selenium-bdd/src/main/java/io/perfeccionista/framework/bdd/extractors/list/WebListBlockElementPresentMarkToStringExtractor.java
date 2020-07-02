package io.perfeccionista.framework.bdd.extractors.list;

import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.extractor.list.WebListBlockElementPresentMarkExtractor;
import io.perfeccionista.framework.pagefactory.extractor.list.WebListBlockValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilterResult;

public class WebListBlockElementPresentMarkToStringExtractor implements WebListBlockValueExtractor<String> {

    private final String elementName;

    public WebListBlockElementPresentMarkToStringExtractor(String elementName) {
        this.elementName = elementName;
    }

    @Override
    public MultipleResult<String> extractValues(WebList element, WebListFilterResult filterResult) {
        return new WebListBlockElementPresentMarkExtractor(elementName)
                .extractValues(element, filterResult)
                .convert(presentMark -> presentMark ? "1" : "0");
    }

}
