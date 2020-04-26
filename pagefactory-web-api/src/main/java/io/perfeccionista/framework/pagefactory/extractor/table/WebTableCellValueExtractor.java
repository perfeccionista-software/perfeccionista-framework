package io.perfeccionista.framework.pagefactory.extractor.table;

import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.extractor.ValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableFilter;

public interface WebTableCellValueExtractor<T> extends ValueExtractor<WebTable, WebTableFilter, T> {

    MultipleResult<T> extractHeaderValues(WebTable element);

    MultipleResult<T> extractFooterValues(WebTable element);

}
