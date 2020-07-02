package io.perfeccionista.framework.pagefactory.extractor.list;

import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.elements.methods.GetTextAvailable;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilter;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilterResult;

public class WebListBlockElementTextValueExtractor implements WebListBlockValueExtractor<String> {

    private final GetTextAvailable elementMock;
    private final String elementName;

    public WebListBlockElementTextValueExtractor(GetTextAvailable elementMock) {
        this.elementName = null;
        this.elementMock = elementMock;
    }

    public WebListBlockElementTextValueExtractor(String elementName) {
        this.elementName = elementName;
        this.elementMock = null;
    }

    @Override
    public MultipleResult<String> extractValues(WebList element, WebListFilterResult filter) {
        // Вся логика извлечения и фильтрации здесь

        return null;
    }

}
