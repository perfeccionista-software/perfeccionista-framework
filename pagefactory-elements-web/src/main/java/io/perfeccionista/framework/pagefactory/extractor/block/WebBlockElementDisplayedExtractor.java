package io.perfeccionista.framework.pagefactory.extractor.block;

import io.perfeccionista.framework.pagefactory.elements.WebUnorderedList;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.IsDisplayedAvailable;
import io.perfeccionista.framework.pagefactory.extractor.WebBlockValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;

public class WebBlockElementDisplayedExtractor implements WebBlockValueExtractor<Boolean> {

    private final String elementName;
    private final IsDisplayedAvailable elementMock;

    public WebBlockElementDisplayedExtractor(String elementName) {
        this.elementName = elementName;
        this.elementMock = null;
    }

    public WebBlockElementDisplayedExtractor(IsDisplayedAvailable elementMock) {
        this.elementName = null;
        this.elementMock = elementMock;
    }

    @Override
    public WebBlockValueExtractor<Boolean> setHash(String hash) {
        return null;
    }

    @Override
    public SingleResult<Boolean> extractSingleValue(WebUnorderedList element, SingleResult<Integer> filterResult) {
        return null;
    }

    @Override
    public MultipleResult<Boolean> extractMultipleValues(WebUnorderedList element, MultipleResult<Integer> filterResult) {
        return null;
    }
}
