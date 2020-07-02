package io.perfeccionista.framework.pagefactory.extractor.table;

import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.extractor.WebValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableFilterResult;

public interface WebTableCellValueExtractor<T> extends WebValueExtractor<WebTable, WebTableFilterResult, T> {

    MultipleResult<T> extractHeaderValues(WebTable element);

    MultipleResult<T> extractFooterValues(WebTable element);

}
