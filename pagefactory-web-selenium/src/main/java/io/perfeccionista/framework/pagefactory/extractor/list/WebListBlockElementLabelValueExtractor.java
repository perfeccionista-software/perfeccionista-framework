package io.perfeccionista.framework.pagefactory.extractor.list;

import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.elements.methods.GetLabelAvailable;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilter;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilterResult;

public class WebListBlockElementLabelValueExtractor implements WebListBlockValueExtractor<String> {

    private final GetLabelAvailable elementMock;
    private final String elementName;

    public WebListBlockElementLabelValueExtractor(GetLabelAvailable elementMock) {
        this.elementName = null;
        this.elementMock = elementMock;
    }

    public WebListBlockElementLabelValueExtractor(String elementName) {
        this.elementName = elementName;
        this.elementMock = null;
    }

    @Override
    public MultipleResult<String> extractValues(WebList element, WebListFilterResult filter) {
        // Вся логика извлечения и фильтрации здесь

        return null;
    }

}

