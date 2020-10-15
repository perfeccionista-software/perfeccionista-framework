package io.perfeccionista.framework.pagefactory.filter.texttable;

import io.perfeccionista.framework.matcher.result.WebMultipleIndexedResultMatcher;
import io.perfeccionista.framework.pagefactory.elements.WebTextTable;
import io.perfeccionista.framework.pagefactory.extractor.texttable.WebTextTableCellValueExtractor;
import io.perfeccionista.framework.result.WebMultipleIndexedResult;
import io.perfeccionista.framework.result.WebSingleIndexedResult;
import io.perfeccionista.framework.pagefactory.filter.WebFilter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

// TODO: Implement: public Map<String, SingleResult<String>> extractOne(Set<String> columnNames)
// TODO: Implement: public Map<String, MultipleResult<String>> extractAll(Set<String> columnNames)
public interface WebTextTableFilter extends WebFilter<WebTextTable> {

    @NotNull WebSingleIndexedResult<String, WebTextTable> extractHeader(@NotNull String columnName);

    @NotNull WebSingleIndexedResult<String, WebTextTable> extractOneRow(@NotNull String columnName);

    @NotNull <T> WebSingleIndexedResult<T, WebTextTable> extractOneRow(@NotNull WebTextTableCellValueExtractor<T> extractor);

    @NotNull WebMultipleIndexedResult<String, WebTextTable> extractAllRows(@NotNull String columnName);

    @NotNull <T> WebMultipleIndexedResult<T, WebTextTable> extractAllRows(@NotNull WebTextTableCellValueExtractor<T> extractor);

    @NotNull WebSingleIndexedResult<String, WebTextTable> extractFooter(@NotNull String columnName);

    @Override
    WebTextTableFilter should(@NotNull WebMultipleIndexedResultMatcher<Integer> matcher);

    @Override
    WebTextTableFilter setInitialHash(@Nullable String initialHash);

}
