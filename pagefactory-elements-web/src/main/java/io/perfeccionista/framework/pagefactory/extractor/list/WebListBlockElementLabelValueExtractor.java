package io.perfeccionista.framework.pagefactory.extractor.list;

import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.GetLabelAvailable;
import io.perfeccionista.framework.pagefactory.extractor.WebListBlockValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;

public class WebListBlockElementLabelValueExtractor implements WebListBlockValueExtractor<String> {

    private final GetLabelAvailable elementMock;
    private final String elementName;

    public WebListBlockElementLabelValueExtractor(GetLabelAvailable elementMock) {
        this.elementName = null;
        this.elementMock = elementMock;
    }

    public WebListBlockElementLabelValueExtractor(String elementName) {
        this.elementName = elementName;
        this.elementMock = null;
    }

    @Override
    public WebListBlockElementTextValueExtractor setHash(String hash) {
        return null;
    }

    @Override
    public SingleResult<String> extractSingleValue(WebList element, SingleResult<Integer> filterResult) {
        return null;
    }

    @Override
    public MultipleResult<String> extractMultipleValues(WebList element, MultipleResult<Integer> filterResult) {
        return null;
    }
}

