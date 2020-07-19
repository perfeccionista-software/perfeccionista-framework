package io.perfeccionista.framework.pagefactory.extractor.table;

import io.perfeccionista.framework.pagefactory.elements.WebMappedBlock;
import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableFilter;

// TODO: Для пустого фильтра возвращаем все значения
public class WebTableCellExtractor<T extends WebMappedBlock> implements WebTableCellValueExtractor<T> {

    private final String columnName;
    private final Class<T> blockClass;

    public WebTableCellExtractor(String columnName, Class<T> blockClass) {
        this.columnName = columnName;
        this.blockClass = blockClass;
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