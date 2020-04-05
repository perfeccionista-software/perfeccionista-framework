package io.perfeccionista.framework.pagefactory.extractor.list;

import io.perfeccionista.framework.pagefactory.elements.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.extractor.WebListBlockValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;

public class WebListBlockElementPropertyValueExtractor implements WebListBlockValueExtractor<String> {

    private final String propertyName;
    private final WebChildElement elementMock;
    private final String elementName;

    public WebListBlockElementPropertyValueExtractor(WebChildElement elementMock, String propertyName) {
        this.propertyName = propertyName;
        this.elementName = null;
        this.elementMock = elementMock;
    }

    public WebListBlockElementPropertyValueExtractor(String elementName, String propertyName) {
        this.propertyName = propertyName;
        this.elementName = elementName;
        this.elementMock = null;
    }

    @Override
    public WebListBlockElementPropertyValueExtractor setHash(String hash) {
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
