package io.perfeccionista.framework.bdd.extractors.table;

import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.extractor.table.WebTableCellElementSelectedMarkExtractor;
import io.perfeccionista.framework.pagefactory.extractor.table.WebTableCellValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableFilterResult;

public class WebTableCellElementSelectedMarkToStringExtractor implements WebTableCellValueExtractor<String> {

    protected final String columnName;
    protected final String elementName;

    public WebTableCellElementSelectedMarkToStringExtractor(String columnName, String elementName) {
        this.columnName = columnName;
        this.elementName = elementName;
    }

    @Override
    public MultipleResult<String> extractHeaderValues(WebTable element) {
        return new WebTableCellElementSelectedMarkExtractor(columnName, elementName)
                .extractHeaderValues(element)
                .convert(selectedMark -> selectedMark ? "1" : "0");    }

    @Override
    public MultipleResult<String> extractFooterValues(WebTable element) {
        return new WebTableCellElementSelectedMarkExtractor(columnName, elementName)
                .extractFooterValues(element)
                .convert(selectedMark -> selectedMark ? "1" : "0");
    }

    @Override
    public MultipleResult<String> extractValues(WebTable element, WebTableFilterResult filterResult) {
        return new WebTableCellElementSelectedMarkExtractor(columnName, elementName)
                .extractValues(element, filterResult)
                .convert(selectedMark -> selectedMark ? "1" : "0");
    }

}
