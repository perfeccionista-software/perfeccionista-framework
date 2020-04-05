package io.perfeccionista.framework.pagefactory.extractor.list;

import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.IsEnabledAvailable;
import io.perfeccionista.framework.pagefactory.extractor.WebListBlockValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;

public class WebListBlockElementEnabledMarkExtractor implements WebListBlockValueExtractor<Boolean> {

    private final IsEnabledAvailable elementMock;
    private final String elementName;

    public WebListBlockElementEnabledMarkExtractor(IsEnabledAvailable elementMock) {
        this.elementName = null;
        this.elementMock = elementMock;
    }

    public WebListBlockElementEnabledMarkExtractor(String elementName) {
        this.elementName = elementName;
        this.elementMock = null;
    }

    @Override
    public WebListBlockElementEnabledMarkExtractor setHash(String hash) {
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
