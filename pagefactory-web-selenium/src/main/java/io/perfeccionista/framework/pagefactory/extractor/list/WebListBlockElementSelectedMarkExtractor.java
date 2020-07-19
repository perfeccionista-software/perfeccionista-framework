package io.perfeccionista.framework.pagefactory.extractor.list;

import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.elements.methods.IsSelectedAvailable;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilter;

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
    public MultipleResult<Boolean> extractValues(WebList element, WebListFilter filter) {
        // Вся логика извлечения и фильтрации здесь

        return null;
    }

}
