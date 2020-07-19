package io.perfeccionista.framework.pagefactory.extractor.table;

import io.perfeccionista.framework.pagefactory.elements.WebMappedBlock;
import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableFilter;

public class WebTableRowExtractor implements WebTableCellValueExtractor<WebMappedBlock> {

    @Override
    public MultipleResult<WebMappedBlock> extractHeaderValues(WebTable element) {
        // Вся логика извлечения и фильтрации здесь

        return null;
    }

    @Override
    public MultipleResult<WebMappedBlock> extractValues(WebTable element, WebTableFilter filter) {
        // Вся логика извлечения и фильтрации здесь

        return null;
    }

    @Override
    public MultipleResult<WebMappedBlock> extractFooterValues(WebTable element) {
        // Вся логика извлечения и фильтрации здесь

        return null;
    }

}
