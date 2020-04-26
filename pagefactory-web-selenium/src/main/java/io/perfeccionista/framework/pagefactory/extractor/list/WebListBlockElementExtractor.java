package io.perfeccionista.framework.pagefactory.extractor.list;

import io.perfeccionista.framework.pagefactory.elements.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilter;

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
    public MultipleResult<T> extractValues(WebList element, WebListFilter filter) {
        // Вся логика извлечения и фильтрации здесь

        return null;
    }

}
