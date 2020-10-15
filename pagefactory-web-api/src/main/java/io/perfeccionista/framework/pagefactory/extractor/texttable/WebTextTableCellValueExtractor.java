package io.perfeccionista.framework.pagefactory.extractor.texttable;

import io.perfeccionista.framework.pagefactory.elements.WebTextTable;
import io.perfeccionista.framework.pagefactory.extractor.WebValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.texttable.WebTextTableFilter;

public interface WebTextTableCellValueExtractor<T> extends WebValueExtractor<WebTextTable, WebTextTableFilter, T> {

    WebTextTableCellValueExtractor<T> fromHeader();

    WebTextTableCellValueExtractor<T> fromFooter();

}
