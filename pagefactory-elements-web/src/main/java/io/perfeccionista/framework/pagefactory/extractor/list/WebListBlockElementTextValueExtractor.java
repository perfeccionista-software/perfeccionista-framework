package io.perfeccionista.framework.pagefactory.extractor.list;

import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.GetTextAvailable;
import io.perfeccionista.framework.pagefactory.extractor.WebListBlockValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;

public class WebListBlockElementTextValueExtractor implements WebListBlockValueExtractor<String> {

    private final GetTextAvailable elementMock;
    private final String elementName;

    public WebListBlockElementTextValueExtractor(GetTextAvailable elementMock) {
        this.elementName = null;
        this.elementMock = elementMock;
    }

    public WebListBlockElementTextValueExtractor(String elementName) {
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
