package io.perfeccionista.framework.pagefactory.extractor.block;

import io.perfeccionista.framework.pagefactory.elements.WebUnorderedList;
import io.perfeccionista.framework.pagefactory.extractor.WebBlockValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;

public class WebBlockElementExtractor<T> implements WebBlockValueExtractor<T> {

    private final String elementName;
    private final T elementMock;
    private final Class<T> returnType;

    public WebBlockElementExtractor(T elementMock) {
        this.elementName = null;
        this.elementMock = elementMock;
        this.returnType = null;
    }

    public WebBlockElementExtractor(String elementName, Class<T> returnType) {
        this.elementName = elementName;
        this.elementMock = null;
        this.returnType = returnType;
    }

    @Override
    public WebBlockValueExtractor<T> setHash(String hash) {
        return null;
    }

    @Override
    public SingleResult<T> extractSingleValue(WebUnorderedList element, SingleResult<Integer> filterResult) {
        return null;
    }

    @Override
    public MultipleResult<T> extractMultipleValues(WebUnorderedList element, MultipleResult<Integer> filterResult) {
        return null;
    }

}
