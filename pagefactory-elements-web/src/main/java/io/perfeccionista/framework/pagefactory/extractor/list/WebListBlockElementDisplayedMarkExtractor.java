package io.perfeccionista.framework.pagefactory.extractor.list;

import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.IsDisplayedAvailable;
import io.perfeccionista.framework.pagefactory.extractor.WebListBlockValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;

public class WebListBlockElementDisplayedMarkExtractor implements WebListBlockValueExtractor<Boolean> {

    private final IsDisplayedAvailable elementMock;
    private final String elementName;

    public WebListBlockElementDisplayedMarkExtractor(IsDisplayedAvailable elementMock) {
        this.elementName = null;
        this.elementMock = elementMock;
    }

    public WebListBlockElementDisplayedMarkExtractor(String elementName) {
        this.elementName = elementName;
        this.elementMock = null;
    }

    @Override
    public WebListBlockElementDisplayedMarkExtractor setHash(String hash) {
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
