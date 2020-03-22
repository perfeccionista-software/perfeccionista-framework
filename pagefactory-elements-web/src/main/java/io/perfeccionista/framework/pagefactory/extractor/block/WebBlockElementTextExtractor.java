package io.perfeccionista.framework.pagefactory.extractor.block;

import io.perfeccionista.framework.pagefactory.elements.WebUnorderedList;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.GetTextAvailable;
import io.perfeccionista.framework.pagefactory.extractor.WebBlockValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;

public class WebBlockElementTextExtractor implements WebBlockValueExtractor<String> {

    private final String elementName;
    private final GetTextAvailable elementMock;

    public WebBlockElementTextExtractor(GetTextAvailable elementMock) {
        this.elementName = null;
        this.elementMock = elementMock;
    }

    public WebBlockElementTextExtractor(String elementName) {
        this.elementName = elementName;
        this.elementMock = null;
    }

    @Override
    public WebBlockValueExtractor<String> setHash(String hash) {
        return null;
    }

    @Override
    public SingleResult<String> extractSingleValue(WebUnorderedList element, SingleResult<Integer> filterResult) {
        return null;
    }

    @Override
    public MultipleResult<String> extractMultipleValues(WebUnorderedList element, MultipleResult<Integer> filterResult) {
        return null;
    }
}
