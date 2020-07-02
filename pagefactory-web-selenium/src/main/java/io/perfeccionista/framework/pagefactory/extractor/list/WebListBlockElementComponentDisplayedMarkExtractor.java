package io.perfeccionista.framework.pagefactory.extractor.list;

import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilter;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilterResult;

public class WebListBlockElementComponentDisplayedMarkExtractor implements WebListBlockValueExtractor<Boolean> {

    private final String componentName;
    private final WebChildElement elementMock;
    private final String elementName;

    public WebListBlockElementComponentDisplayedMarkExtractor(WebChildElement elementMock, String componentName) {
        this.componentName = componentName;
        this.elementName = null;
        this.elementMock = elementMock;
    }

    public WebListBlockElementComponentDisplayedMarkExtractor(String elementName, String componentName) {
        this.componentName = componentName;
        this.elementName = elementName;
        this.elementMock = null;
    }

    @Override
    public MultipleResult<Boolean> extractValues(WebList element, WebListFilterResult filterResult) {
        // Вся логика извлечения и фильтрации здесь

        return null;
    }

}
