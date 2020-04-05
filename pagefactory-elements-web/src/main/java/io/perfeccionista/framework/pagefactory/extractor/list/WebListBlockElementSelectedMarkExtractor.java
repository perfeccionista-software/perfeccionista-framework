package io.perfeccionista.framework.pagefactory.extractor.list;

import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.IsSelectedAvailable;
import io.perfeccionista.framework.pagefactory.extractor.WebListBlockValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;

public class WebListBlockElementSelectedMarkExtractor implements WebListBlockValueExtractor<Boolean> {

    private final IsSelectedAvailable elementMock;
    private final String elementName;

    public WebListBlockElementSelectedMarkExtractor(IsSelectedAvailable elementMock) {
        this.elementName = null;
        this.elementMock = elementMock;
    }

    public WebListBlockElementSelectedMarkExtractor(String elementName) {
        this.elementName = elementName;
        this.elementMock = null;
    }

    @Override
    public WebListBlockElementSelectedMarkExtractor setHash(String hash) {
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
