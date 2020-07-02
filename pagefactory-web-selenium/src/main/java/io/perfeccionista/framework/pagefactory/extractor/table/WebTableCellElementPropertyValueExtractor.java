package io.perfeccionista.framework.pagefactory.extractor.table;

import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableFilter;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableFilterResult;

public class WebTableCellElementPropertyValueExtractor implements WebTableCellValueExtractor<String> {

    private final String columnName;
    private final String propertyName;
    private final WebChildElement elementMock;
    private final String elementName;

    public WebTableCellElementPropertyValueExtractor(String columnName, WebChildElement elementMock, String propertyName) {
        this.columnName = columnName;
        this.propertyName = propertyName;
        this.elementName = null;
        this.elementMock = elementMock;
    }

    public WebTableCellElementPropertyValueExtractor(String columnName, String elementName, String propertyName) {
        this.columnName = columnName;
        this.propertyName = propertyName;
        this.elementName = elementName;
        this.elementMock = null;
    }

    @Override
    public MultipleResult<String> extractHeaderValues(WebTable element) {
        // Вся логика извлечения и фильтрации здесь

        return null;
    }

    @Override
    public MultipleResult<String> extractValues(WebTable element, WebTableFilterResult filter) {
        // Вся логика извлечения и фильтрации здесь

        return null;
    }

    @Override
    public MultipleResult<String> extractFooterValues(WebTable element) {
        // Вся логика извлечения и фильтрации здесь

        return null;
    }

}

