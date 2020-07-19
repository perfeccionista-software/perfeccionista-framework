package io.perfeccionista.framework.pagefactory.filter.texttable;

import io.perfeccionista.framework.pagefactory.extractor.texttable.WebTextTableCellValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.FilterResult;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;
import io.perfeccionista.framework.value.number.NumberValue;
import org.jetbrains.annotations.NotNull;

public interface WebTextTableFilterResult extends FilterResult {

    @NotNull String getHash();

    @NotNull SingleResult<String> extractHeader(@NotNull String columnName);

    @NotNull SingleResult<String> extractOneRow(@NotNull String columnName);

    @NotNull <T> SingleResult<T> extractOneRow(@NotNull WebTextTableCellValueExtractor<T> extractor);

    // TODO: Implement: public Map<String, SingleResult<String>> extractOne(Set<String> columnNames)

    @NotNull MultipleResult<String> extractAllRows(@NotNull String columnName);

    @NotNull <T> MultipleResult<T> extractAllRows(@NotNull WebTextTableCellValueExtractor<T> extractor);

    // TODO: Implement: public Map<String, MultipleResult<String>> extractAll(Set<String> columnNames)

    @NotNull SingleResult<String> extractFooter(@NotNull String columnName);

    WebTextTableFilterResult shouldHaveSize(@NotNull NumberValue<Integer> expectedSize);

}
