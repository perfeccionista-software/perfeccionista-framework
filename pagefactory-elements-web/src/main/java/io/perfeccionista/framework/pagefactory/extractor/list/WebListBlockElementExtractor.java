package io.perfeccionista.framework.pagefactory.extractor.list;

import io.perfeccionista.framework.pagefactory.elements.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.extractor.WebListBlockValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;

public class WebListBlockElementExtractor<T extends WebChildElement> implements WebListBlockValueExtractor<T> {

    private final T elementMock;
    private final String elementName;
    private final Class<T> returnType;

    public WebListBlockElementExtractor(T elementMock) {
        this.elementName = null;
        this.elementMock = elementMock;
        this.returnType = null;
    }

    public WebListBlockElementExtractor(String elementName, Class<T> returnType) {
        this.elementName = elementName;
        this.elementMock = null;
        this.returnType = returnType;
    }

    @Override
    public WebListBlockElementExtractor<T> setHash(String hash) {
        return null;
    }

    @Override
    public SingleResult<T> extractSingleValue(WebList element, SingleResult<Integer> filterResult) {
        return null;
    }

    @Override
    public MultipleResult<T> extractMultipleValues(WebList element, MultipleResult<Integer> filterResult) {
        return null;
    }

}
