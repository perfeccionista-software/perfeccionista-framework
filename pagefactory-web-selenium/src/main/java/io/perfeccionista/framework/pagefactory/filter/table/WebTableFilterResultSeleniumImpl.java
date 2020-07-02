package io.perfeccionista.framework.pagefactory.filter.table;

import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.extractor.table.WebTableCellValueExtractor;
import io.perfeccionista.framework.pagefactory.extractor.table.WebTableRowIndexExtractor;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;
import io.perfeccionista.framework.value.number.NumberValue;

public class WebTableFilterResultSeleniumImpl implements WebTableFilterResult {

    private final WebTable element;
    private final WebTableFilter filter;
    private String tableHash = null;

    public WebTableFilterResultSeleniumImpl(WebTable element, WebTableFilter filter) {
        this.element = element;
        this.filter = filter;
    }

    @Override
    public String getHash() {
        return tableHash;
    }

    public <T> SingleResult<T> extractHeader(WebTableCellValueExtractor<T> extractor) {
        return extractor
                .extractHeaderValues(element)
                .singleResult();
    }

    public <T> SingleResult<T> extractOneRow(WebTableCellValueExtractor<T> extractor) {
        return extractor
                .extractValues(element, filter.filter(element))
                .singleResult();
    }

    // TODO: Implement: public Map<String, SingleResult<T>> extractOneRow(Map<String, WebTableCellValueExtractor<T>> columnExtractors)

    public <T> MultipleResult<T> extractAllRows(WebTableCellValueExtractor<T> extractor) {
        return extractor
                .extractValues(element, filter.filter(element));
    }

    // TODO: Implement: public Map<String, MultipleResult<T>> extractAllRows(Map<String, WebTableCellValueExtractor<T>> columnExtractors)

    public <T> SingleResult<T> extractFooter(WebTableCellValueExtractor<T> extractor) {
        return extractor
                .extractFooterValues(element)
                .singleResult();
    }

    public WebTableFilterResult shouldHaveSize(NumberValue<Integer> integerValue) {
        extractAllRows(new WebTableRowIndexExtractor())
                .shouldHaveSize(integerValue);
        return this;
    }

}
