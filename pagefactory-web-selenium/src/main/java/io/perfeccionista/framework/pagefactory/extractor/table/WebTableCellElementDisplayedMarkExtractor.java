package io.perfeccionista.framework.pagefactory.extractor.table;

import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.elements.methods.IsDisplayedAvailable;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableFilter;

public class WebTableCellElementDisplayedMarkExtractor implements WebTableCellValueExtractor<Boolean> {

    private final String columnName;
    private final IsDisplayedAvailable elementMock;
    private final String elementName;

    public WebTableCellElementDisplayedMarkExtractor(String columnName, IsDisplayedAvailable elementMock) {
        this.columnName = columnName;
        this.elementName = null;
        this.elementMock = elementMock;
    }

    public WebTableCellElementDisplayedMarkExtractor(String columnName, String elementName) {
        this.columnName = columnName;
        this.elementName = elementName;
        this.elementMock = null;
    }

    @Override
    public MultipleResult<Boolean> extractHeaderValues(WebTable element) {
        // Вся логика извлечения и фильтрации здесь

        return null;
    }

    @Override
    public MultipleResult<Boolean> extractValues(WebTable element, WebTableFilter filter) {
        // Вся логика извлечения и фильтрации здесь

        return null;
    }

    @Override
    public MultipleResult<Boolean> extractFooterValues(WebTable element) {
        // Вся логика извлечения и фильтрации здесь

        return null;
    }

}
