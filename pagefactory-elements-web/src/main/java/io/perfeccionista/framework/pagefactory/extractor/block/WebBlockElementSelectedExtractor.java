package io.perfeccionista.framework.pagefactory.extractor.block;

import io.perfeccionista.framework.pagefactory.elements.WebUnorderedList;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.IsSelectedAvailable;
import io.perfeccionista.framework.pagefactory.extractor.WebBlockValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;

public class WebBlockElementSelectedExtractor implements WebBlockValueExtractor<Boolean> {

    private final String elementName;
    private final IsSelectedAvailable elementMock;

    public WebBlockElementSelectedExtractor(String elementName) {
        this.elementName = elementName;
        this.elementMock = null;
    }

    public WebBlockElementSelectedExtractor(IsSelectedAvailable elementMock) {
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
