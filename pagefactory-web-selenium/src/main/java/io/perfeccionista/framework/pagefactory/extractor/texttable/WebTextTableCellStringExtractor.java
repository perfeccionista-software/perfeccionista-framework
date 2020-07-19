package io.perfeccionista.framework.pagefactory.extractor.texttable;

import io.perfeccionista.framework.pagefactory.elements.WebTextTable;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.texttable.WebTextTableFilter;

// TODO: Для пустого фильтра возвращаем все значения
public class WebTextTableCellStringExtractor implements WebTextTableCellValueExtractor<String> {

    private final String columnName;

    public WebTextTableCellStringExtractor(String columnName) {
        this.columnName = columnName;
    }

    @Override
    public MultipleResult<String> extractHeaderValues(WebTextTable element) {
        // Вся логика извлечения и фильтрации здесь

        return null;
    }

    @Override
    public MultipleResult<String> extractValues(WebTextTable element, WebTextTableFilter filter) {
        // Вся логика извлечения и фильтрации здесь

        return null;
    }

    @Override
    public MultipleResult<String> extractFooterValues(WebTextTable element) {
        // Вся логика извлечения и фильтрации здесь

        return null;
    }

}
