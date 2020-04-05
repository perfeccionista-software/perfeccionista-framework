package io.perfeccionista.framework.pagefactory.extractor.table;

import io.perfeccionista.framework.pagefactory.elements.WebMappedBlock;
import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.extractor.WebTableCellValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;

public class WebTableCellExtractor<T extends WebMappedBlock> implements WebTableCellValueExtractor<T> {

    private final String columnName;
    private final Class<T> blockClass;

    public WebTableCellExtractor(String columnName, Class<T> blockClass) {
        this.columnName = columnName;
        this.blockClass = blockClass;
    }

    @Override
    public WebTableCellExtractor<T> setHash(String hash) {
        return null;
    }

    @Override
    public SingleResult<T> extractSingleHeaderValue(WebTable element) {
        return null;
    }

    @Override
    public SingleResult<T> extractSingleValue(WebTable element, SingleResult<Integer> filterResult) {
        return null;
    }

    @Override
    public MultipleResult<T> extractMultipleValues(WebTable element, MultipleResult<Integer> filterResult) {
        return null;
    }

    @Override
    public SingleResult<T> extractSingleFooterValue(WebTable element) {
        return null;
    }

}
