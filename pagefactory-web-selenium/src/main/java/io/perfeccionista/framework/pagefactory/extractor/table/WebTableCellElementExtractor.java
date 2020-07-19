package io.perfeccionista.framework.pagefactory.extractor.table;

import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableFilter;

public class WebTableCellElementExtractor<T extends WebChildElement> implements WebTableCellValueExtractor<T> {

    private final String columnName;
    private final T elementMock;
    private final String elementName;
    private final Class<T> returnType;

    public WebTableCellElementExtractor(String columnName, T elementMock) {
        this.columnName = columnName;
        this.elementName = null;
        this.elementMock = elementMock;
        this.returnType = null;
    }

    public WebTableCellElementExtractor(String columnName, String elementName, Class<T> returnType) {
        this.columnName = columnName;
        this.elementName = elementName;
        this.elementMock = null;
        this.returnType = returnType;
    }

    @Override
    public MultipleResult<T> extractHeaderValues(WebTable element) {
        // Вся логика извлечения и фильтрации здесь

        return null;
    }

    @Override
    public MultipleResult<T> extractValues(WebTable element, WebTableFilter filter) {
        // Вся логика извлечения и фильтрации здесь

        return null;
    }

    @Override
    public MultipleResult<T> extractFooterValues(WebTable element) {
        // Вся логика извлечения и фильтрации здесь

        return null;
    }

}
