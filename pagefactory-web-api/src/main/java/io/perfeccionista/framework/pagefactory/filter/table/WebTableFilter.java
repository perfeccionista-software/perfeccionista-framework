package io.perfeccionista.framework.pagefactory.filter.table;

import io.perfeccionista.framework.pagefactory.extractor.table.WebTableCellValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;
import io.perfeccionista.framework.pagefactory.filter.WebFilter;
import io.perfeccionista.framework.value.number.NumberValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

// TODO: Implement: public Map<String, SingleResult<T>> extractOneRow(Map<String, WebTableCellValueExtractor<T>> columnExtractors)
// TODO: Implement: public Map<String, MultipleResult<T>> extractAllRows(Map<String, WebTableCellValueExtractor<T>> columnExtractors)
public interface WebTableFilter extends WebFilter {

    @Override
    WebTableFilter setInitialHash(@Nullable String initialHash);

    @Override
    WebTableFilter shouldHaveSize(@NotNull NumberValue<Integer> expectedSize);

    @NotNull <T> SingleResult<T> extractHeader(@NotNull WebTableCellValueExtractor<T> extractor);

    @NotNull <T> SingleResult<T> extractOneRow(@NotNull WebTableCellValueExtractor<T> extractor);

    @NotNull <T> MultipleResult<T> extractAllRows(@NotNull WebTableCellValueExtractor<T> extractor);

    @NotNull <T> SingleResult<T> extractFooter(@NotNull WebTableCellValueExtractor<T> extractor);

}
