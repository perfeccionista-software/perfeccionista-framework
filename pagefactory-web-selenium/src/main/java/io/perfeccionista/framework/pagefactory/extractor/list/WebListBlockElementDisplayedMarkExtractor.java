package io.perfeccionista.framework.pagefactory.extractor.list;

import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.elements.methods.IsDisplayedAvailable;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilter;

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
    public MultipleResult<Boolean> extractValues(WebList element, WebListFilter filter) {
        // Вся логика извлечения и фильтрации здесь

        return null;
    }

}
