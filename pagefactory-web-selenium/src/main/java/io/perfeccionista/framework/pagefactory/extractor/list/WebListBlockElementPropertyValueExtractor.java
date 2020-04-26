package io.perfeccionista.framework.pagefactory.extractor.list;

import io.perfeccionista.framework.pagefactory.elements.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilter;

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
    public MultipleResult<String> extractValues(WebList element, WebListFilter filter) {
        // Вся логика извлечения и фильтрации здесь

        return null;
    }

}
