package io.perfeccionista.framework.bdd.extractors.table;

import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.extractor.table.WebTableCellElementEnabledMarkExtractor;
import io.perfeccionista.framework.pagefactory.extractor.table.WebTableCellValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableFilter;

public class WebTableCellElementEnabledMarkToStringExtractor implements WebTableCellValueExtractor<String> {

    protected final String columnName;
    protected final String elementName;

    public WebTableCellElementEnabledMarkToStringExtractor(String columnName, String elementName) {
        this.columnName = columnName;
        this.elementName = elementName;
    }

    @Override
    public MultipleResult<String> extractHeaderValues(WebTable element) {
        return new WebTableCellElementEnabledMarkExtractor(columnName, elementName)
                .extractHeaderValues(element)
                .convert(enabledMark -> enabledMark ? "1" : "0");    }

    @Override
    public MultipleResult<String> extractFooterValues(WebTable element) {
        return new WebTableCellElementEnabledMarkExtractor(columnName, elementName)
                .extractFooterValues(element)
                .convert(enabledMark -> enabledMark ? "1" : "0");
    }

    @Override
    public MultipleResult<String> extractValues(WebTable element, WebTableFilter filter) {
        return new WebTableCellElementEnabledMarkExtractor(columnName, elementName)
                .extractValues(element, filter)
                .convert(enabledMark -> enabledMark ? "1" : "0");
    }

}

