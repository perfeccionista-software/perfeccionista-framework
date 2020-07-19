package io.perfeccionista.framework.pagefactory.filter.texttable;

import io.perfeccionista.framework.pagefactory.elements.WebTextTable;
import io.perfeccionista.framework.pagefactory.extractor.texttable.WebTextTableCellStringExtractor;
import io.perfeccionista.framework.pagefactory.extractor.texttable.WebTextTableCellValueExtractor;
import io.perfeccionista.framework.pagefactory.extractor.texttable.WebTextTableRowIndexExtractor;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;
import io.perfeccionista.framework.pagefactory.filter.WebFilterResult;
import io.perfeccionista.framework.value.number.NumberValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class WebTextTableFilterSeleniumImpl implements WebTextTableFilter {

    private final WebTextTable element;
    private final WebTextTableFilterBuilder filter;
    private String textTableHash = null;

    public WebTextTableFilterSeleniumImpl(WebTextTable element, WebTextTableFilterBuilder filter) {
        this.element = element;
        this.filter = filter;
    }

    @Override
    public WebTextTableFilter setInitialHash(@Nullable String initialHash) {
        return null;
    }

    @Override
    public @NotNull WebFilterResult getResult() {
        return null;
    }

    public SingleResult<String> extractHeader(String columnName) {
        return new WebTextTableCellStringExtractor(columnName)
                .extractHeaderValues(element)
                .singleResult();
    }

    public SingleResult<String> extractOneRow(String columnName) {
        return new WebTextTableCellStringExtractor(columnName)
                .extractValues(element, filter.build(element))
                .singleResult();
    }

    @Override
    public <T> SingleResult<T> extractOneRow(WebTextTableCellValueExtractor<T> extractor) {
        return extractor
                .extractValues(element, filter.build(element))
                .singleResult();
    }

    // TODO: Implement: public Map<String, SingleResult<String>> extractOne(Set<String> columnNames)

    public MultipleResult<String> extractAllRows(String columnName) {
        return new WebTextTableCellStringExtractor(columnName)
                .extractValues(element, filter.build(element));
    }

    @Override
    public <T> MultipleResult<T> extractAllRows(WebTextTableCellValueExtractor<T> extractor) {
        return extractor
                .extractValues(element, filter.build(element));
    }

    // TODO: Implement: public Map<String, MultipleResult<String>> extractAll(Set<String> columnNames)

    public SingleResult<String> extractFooter(String columnName) {
        return new WebTextTableCellStringExtractor(columnName)
                .extractFooterValues(element)
                .singleResult();
    }

    public WebTextTableFilter shouldHaveSize(NumberValue<Integer> expectedSize) {
        new WebTextTableRowIndexExtractor()
                .extractValues(element, filter.build(element))
                .shouldHaveSize(expectedSize);
        return this;
    }

}
