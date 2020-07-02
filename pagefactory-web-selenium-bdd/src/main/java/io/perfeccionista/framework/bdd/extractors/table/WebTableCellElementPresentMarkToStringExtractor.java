package io.perfeccionista.framework.bdd.extractors.table;

import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.extractor.table.WebTableCellElementPresentMarkExtractor;
import io.perfeccionista.framework.pagefactory.extractor.table.WebTableCellValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableFilterResult;

public class WebTableCellElementPresentMarkToStringExtractor implements WebTableCellValueExtractor<String> {

    protected final String columnName;
    protected final String elementName;

    public WebTableCellElementPresentMarkToStringExtractor(String columnName, String elementName) {
        this.columnName = columnName;
        this.elementName = elementName;
    }

    @Override
    public MultipleResult<String> extractHeaderValues(WebTable element) {
        return new WebTableCellElementPresentMarkExtractor(columnName, elementName)
                .extractHeaderValues(element)
                .convert(presentMark -> presentMark ? "1" : "0");    }

    @Override
    public MultipleResult<String> extractFooterValues(WebTable element) {
        return new WebTableCellElementPresentMarkExtractor(columnName, elementName)
                .extractFooterValues(element)
                .convert(presentMark -> presentMark ? "1" : "0");
    }

    @Override
    public MultipleResult<String> extractValues(WebTable element, WebTableFilterResult filterResult) {
        return new WebTableCellElementPresentMarkExtractor(columnName, elementName)
                .extractValues(element, filterResult)
                .convert(presentMark -> presentMark ? "1" : "0");
    }

}
