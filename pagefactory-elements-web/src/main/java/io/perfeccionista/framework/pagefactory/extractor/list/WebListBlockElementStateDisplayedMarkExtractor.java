package io.perfeccionista.framework.pagefactory.extractor.list;

import io.perfeccionista.framework.pagefactory.elements.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.extractor.WebListBlockValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;

public class WebListBlockElementStateDisplayedMarkExtractor implements WebListBlockValueExtractor<Boolean> {

    private final String stateName;
    private final WebChildElement elementMock;
    private final String elementName;

    public WebListBlockElementStateDisplayedMarkExtractor(WebChildElement elementMock, String stateName) {
        this.stateName = stateName;
        this.elementName = null;
        this.elementMock = elementMock;
    }

    public WebListBlockElementStateDisplayedMarkExtractor(String elementName, String stateName) {
        this.stateName = stateName;
        this.elementName = elementName;
        this.elementMock = null;
    }

    @Override
    public WebListBlockElementStateDisplayedMarkExtractor setHash(String hash) {
        return null;
    }

    @Override
    public SingleResult<Boolean> extractSingleValue(WebList element, SingleResult<Integer> filterResult) {
        return null;
    }

    @Override
    public MultipleResult<Boolean> extractMultipleValues(WebList element, MultipleResult<Integer> filterResult) {
        return null;
    }
}
