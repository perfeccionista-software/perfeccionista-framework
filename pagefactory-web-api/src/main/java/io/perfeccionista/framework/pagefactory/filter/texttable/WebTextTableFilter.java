package io.perfeccionista.framework.pagefactory.filter.texttable;

import io.perfeccionista.framework.pagefactory.extractor.texttable.WebTextTableCellValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;
import io.perfeccionista.framework.pagefactory.filter.WebFilter;
import io.perfeccionista.framework.value.number.NumberValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

// TODO: Implement: public Map<String, SingleResult<String>> extractOne(Set<String> columnNames)
// TODO: Implement: public Map<String, MultipleResult<String>> extractAll(Set<String> columnNames)
public interface WebTextTableFilter extends WebFilter {

    @Override
    WebTextTableFilter setInitialHash(@Nullable String initialHash);

    @Override
    WebTextTableFilter shouldHaveSize(@NotNull NumberValue<Integer> expectedSize);

    @NotNull SingleResult<String> extractHeader(@NotNull String columnName);

    @NotNull SingleResult<String> extractOneRow(@NotNull String columnName);

    @NotNull <T> SingleResult<T> extractOneRow(@NotNull WebTextTableCellValueExtractor<T> extractor);

    @NotNull MultipleResult<String> extractAllRows(@NotNull String columnName);

    @NotNull <T> MultipleResult<T> extractAllRows(@NotNull WebTextTableCellValueExtractor<T> extractor);

    @NotNull SingleResult<String> extractFooter(@NotNull String columnName);

}
