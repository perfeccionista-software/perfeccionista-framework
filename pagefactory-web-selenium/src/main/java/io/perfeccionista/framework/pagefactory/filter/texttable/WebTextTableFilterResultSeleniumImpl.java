package io.perfeccionista.framework.pagefactory.filter.texttable;

import io.perfeccionista.framework.pagefactory.elements.WebTextTable;
import io.perfeccionista.framework.pagefactory.extractor.texttable.WebTextTableCellStringExtractor;
import io.perfeccionista.framework.pagefactory.extractor.texttable.WebTextTableCellValueExtractor;
import io.perfeccionista.framework.pagefactory.extractor.texttable.WebTextTableRowIndexExtractor;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;
import io.perfeccionista.framework.value.number.NumberValue;

public class WebTextTableFilterResultSeleniumImpl implements WebTextTableFilterResult {

    private final WebTextTable element;
    private final WebTextTableFilter filter;
    private String textTableHash = null;

    public WebTextTableFilterResultSeleniumImpl(WebTextTable element, WebTextTableFilter filter) {
        this.element = element;
        this.filter = filter;
    }

    @Override
    public String getHash() {
        return textTableHash;
    }

    public SingleResult<String> extractHeader(String columnName) {
        return new WebTextTableCellStringExtractor(columnName)
                .extractHeaderValues(element)
                .singleResult();
    }

    public SingleResult<String> extractOneRow(String columnName) {
        return new WebTextTableCellStringExtractor(columnName)
                .extractValues(element, filter.filter(element))
                .singleResult();
    }

    @Override
    public <T> SingleResult<T> extractOneRow(WebTextTableCellValueExtractor<T> extractor) {
        return extractor
                .extractValues(element, filter.filter(element))
                .singleResult();
    }

    // TODO: Implement: public Map<String, SingleResult<String>> extractOne(Set<String> columnNames)

    public MultipleResult<String> extractAllRows(String columnName) {
        return new WebTextTableCellStringExtractor(columnName)
                .extractValues(element, filter.filter(element));
    }

    @Override
    public <T> MultipleResult<T> extractAllRows(WebTextTableCellValueExtractor<T> extractor) {
        return extractor
                .extractValues(element, filter.filter(element));
    }

    // TODO: Implement: public Map<String, MultipleResult<String>> extractAll(Set<String> columnNames)

    public SingleResult<String> extractFooter(String columnName) {
        return new WebTextTableCellStringExtractor(columnName)
                .extractFooterValues(element)
                .singleResult();
    }

    public WebTextTableFilterResult shouldHaveSize(NumberValue<Integer> expectedSize) {
        new WebTextTableRowIndexExtractor()
                .extractValues(element, filter.filter(element))
                .shouldHaveSize(expectedSize);
        return this;
    }

}
