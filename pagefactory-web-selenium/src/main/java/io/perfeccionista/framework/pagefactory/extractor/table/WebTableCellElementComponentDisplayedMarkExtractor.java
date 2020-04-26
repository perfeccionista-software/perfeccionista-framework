package io.perfeccionista.framework.pagefactory.extractor.table;

import io.perfeccionista.framework.pagefactory.elements.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableFilter;

public class WebTableCellElementComponentDisplayedMarkExtractor implements WebTableCellValueExtractor<String> {

    private final String columnName;
    private final String componentName;
    private final WebChildElement elementMock;
    private final String elementName;

    public WebTableCellElementComponentDisplayedMarkExtractor(String columnName, WebChildElement elementMock, String componentName) {
        this.columnName = columnName;
        this.componentName = componentName;
        this.elementName = null;
        this.elementMock = elementMock;
    }

    public WebTableCellElementComponentDisplayedMarkExtractor(String columnName, String elementName, String componentName) {
        this.columnName = columnName;
        this.componentName = componentName;
        this.elementName = elementName;
        this.elementMock = null;
    }

    @Override
    public MultipleResult<String> extractHeaderValues(WebTable element) {
        // Вся логика извлечения и фильтрации здесь

        return null;
    }

    @Override
    public MultipleResult<String> extractValues(WebTable element, WebTableFilter filter) {
        // Вся логика извлечения и фильтрации здесь

        return null;
    }

    @Override
    public MultipleResult<String> extractFooterValues(WebTable element) {
        // Вся логика извлечения и фильтрации здесь

        return null;
    }

}