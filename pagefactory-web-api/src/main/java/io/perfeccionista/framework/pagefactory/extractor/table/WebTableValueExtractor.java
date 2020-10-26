package io.perfeccionista.framework.pagefactory.extractor.table;

import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.extractor.WebValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableFilter;

public interface WebTableValueExtractor<T> extends WebValueExtractor<WebTable, WebTableFilter, T> {

    WebTableValueExtractor<T> fromHeader();

    WebTableValueExtractor<T> fromFooter();

}
