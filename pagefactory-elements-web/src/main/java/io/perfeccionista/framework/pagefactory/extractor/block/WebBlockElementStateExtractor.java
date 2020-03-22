package io.perfeccionista.framework.pagefactory.extractor.block;

import io.perfeccionista.framework.pagefactory.elements.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.WebUnorderedList;
import io.perfeccionista.framework.pagefactory.extractor.WebBlockValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;

public class WebBlockElementStateExtractor implements WebBlockValueExtractor<Boolean> {

    private final String stateName;
    private final String elementName;
    private final WebChildElement elementMock;

    public WebBlockElementStateExtractor(WebChildElement elementMock, String stateName) {
        this.stateName = stateName;
        this.elementName = null;
        this.elementMock = elementMock;
    }

    public WebBlockElementStateExtractor(String elementName, String stateName) {
        this.stateName = stateName;
        this.elementName = elementName;
        this.elementMock = null;
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
