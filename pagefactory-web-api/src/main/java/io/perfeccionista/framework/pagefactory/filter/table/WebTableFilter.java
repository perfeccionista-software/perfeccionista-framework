package io.perfeccionista.framework.pagefactory.filter.table;

import io.perfeccionista.framework.matcher.result.WebMultipleIndexedResultMatcher;
import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.extractor.table.WebTableValueExtractor;
import io.perfeccionista.framework.result.WebMultipleIndexedResult;
import io.perfeccionista.framework.result.WebSingleIndexedResult;
import io.perfeccionista.framework.pagefactory.filter.WebFilter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

// TODO: Implement: public Map<String, SingleResult<T>> extractOneRow(Map<String, WebTableCellValueExtractor<T>> columnExtractors)
// TODO: Implement: public Map<String, MultipleResult<T>> extractAllRows(Map<String, WebTableCellValueExtractor<T>> columnExtractors)
public interface WebTableFilter extends WebFilter<WebTable> {

    @NotNull <T> WebSingleIndexedResult<T, WebTable> extractHeader(@NotNull WebTableValueExtractor<T> extractor);

    @NotNull <T> WebSingleIndexedResult<T, WebTable> extractRow(@NotNull WebTableValueExtractor<T> extractor);

    @NotNull <T> WebMultipleIndexedResult<T, WebTable> extractRows(@NotNull WebTableValueExtractor<T> extractor);

    @NotNull <T> WebSingleIndexedResult<T, WebTable> extractFooter(@NotNull WebTableValueExtractor<T> extractor);

    @Override
    WebTableFilter should(@NotNull WebMultipleIndexedResultMatcher<Integer> matcher);

    @Override
    WebTableFilter setInitialHash(@Nullable String initialHash);

}
