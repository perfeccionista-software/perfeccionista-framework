package io.perfeccionista.framework.pagefactory.extractor.texttable;

import io.perfeccionista.framework.pagefactory.elements.WebTextTable;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.texttable.WebTextTableFilter;

// TODO: Для пустого фильтра возвращаем все значения
public class WebTextTableRowIndexExtractor implements WebTextTableCellValueExtractor<Integer> {

    @Override
    public MultipleResult<Integer> extractHeaderValues(WebTextTable element) {
        // Возвращаем 1

        return null;
    }

    @Override
    public MultipleResult<Integer> extractValues(WebTextTable element, WebTextTableFilter filter) {
        // Вся логика извлечения и фильтрации здесь

        return null;
    }

    @Override
    public MultipleResult<Integer> extractFooterValues(WebTextTable element) {
        // Возвращаем 1

        return null;
    }

}
