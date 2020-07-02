package io.perfeccionista.framework.pagefactory.extractor.list;

import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.elements.methods.IsEnabledAvailable;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilterResult;

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
    public MultipleResult<Boolean> extractValues(WebList element, WebListFilterResult filterResult) {
        // Вся логика извлечения и фильтрации здесь

        return null;
    }

}
