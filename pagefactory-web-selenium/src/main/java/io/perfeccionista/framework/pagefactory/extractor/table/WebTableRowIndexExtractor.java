package io.perfeccionista.framework.pagefactory.extractor.table;

import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableFilter;

public class WebTableRowIndexExtractor implements WebTableCellValueExtractor<Integer> {

    @Override
    public MultipleResult<Integer> extractHeaderValues(WebTable element) {
        // Возвращаем 1

        return null;
    }

    @Override
    public MultipleResult<Integer> extractValues(WebTable element, WebTableFilter filter) {
        // Вся логика извлечения и фильтрации здесь

        return null;
    }

    @Override
    public MultipleResult<Integer> extractFooterValues(WebTable element) {
        // Возвращаем 1

        return null;
    }

}
