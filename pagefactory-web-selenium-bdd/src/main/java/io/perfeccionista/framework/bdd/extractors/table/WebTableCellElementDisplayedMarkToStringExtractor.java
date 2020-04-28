package io.perfeccionista.framework.bdd.extractors.table;

import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.extractor.table.WebTableCellElementDisplayedMarkExtractor;
import io.perfeccionista.framework.pagefactory.extractor.table.WebTableCellValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableFilter;

public class WebTableCellElementDisplayedMarkToStringExtractor implements WebTableCellValueExtractor<String> {

    protected final String columnName;
    protected final String elementName;

    public WebTableCellElementDisplayedMarkToStringExtractor(String columnName, String elementName) {
        this.columnName = columnName;
        this.elementName = elementName;
    }

    @Override
    public MultipleResult<String> extractHeaderValues(WebTable element) {
        return new WebTableCellElementDisplayedMarkExtractor(columnName, elementName)
                .extractHeaderValues(element)
                .convert(displayedMark -> displayedMark ? "1" : "0");    }

    @Override
    public MultipleResult<String> extractFooterValues(WebTable element) {
        return new WebTableCellElementDisplayedMarkExtractor(columnName, elementName)
                .extractFooterValues(element)
                .convert(displayedMark -> displayedMark ? "1" : "0");
    }

    @Override
    public MultipleResult<String> extractValues(WebTable element, WebTableFilter filter) {
        return new WebTableCellElementDisplayedMarkExtractor(columnName, elementName)
                .extractValues(element, filter)
                .convert(displayedMark -> displayedMark ? "1" : "0");
    }

}
