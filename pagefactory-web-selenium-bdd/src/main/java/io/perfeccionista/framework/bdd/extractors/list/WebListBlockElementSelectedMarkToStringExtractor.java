package io.perfeccionista.framework.bdd.extractors.list;

import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.extractor.list.WebListBlockElementSelectedMarkExtractor;
import io.perfeccionista.framework.pagefactory.extractor.list.WebListBlockValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilterResult;

public class WebListBlockElementSelectedMarkToStringExtractor implements WebListBlockValueExtractor<String> {

    private final String elementName;

    public WebListBlockElementSelectedMarkToStringExtractor(String elementName) {
        this.elementName = elementName;
    }

    @Override
    public MultipleResult<String> extractValues(WebList element, WebListFilterResult filterResult) {
        return new WebListBlockElementSelectedMarkExtractor(elementName)
                .extractValues(element, filterResult)
                .convert(selectedMark -> selectedMark ? "1" : "0");
    }

}
