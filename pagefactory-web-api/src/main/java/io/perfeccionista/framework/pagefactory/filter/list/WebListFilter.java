package io.perfeccionista.framework.pagefactory.filter.list;

import io.perfeccionista.framework.matcher.result.WebMultipleIndexedResultMatcher;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.extractor.list.WebListBlockValueExtractor;
import io.perfeccionista.framework.result.WebMultipleIndexedResult;
import io.perfeccionista.framework.result.WebSingleIndexedResult;
import io.perfeccionista.framework.pagefactory.filter.WebFilter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface WebListFilter extends WebFilter<WebList> {

    @NotNull <T> WebMultipleIndexedResult<T, WebList> extractAll(@NotNull WebListBlockValueExtractor<T> extractor);

    @NotNull <T> WebSingleIndexedResult<T, WebList> extractOne(@NotNull WebListBlockValueExtractor<T> extractor);

    @Override
    WebListFilter should(@NotNull WebMultipleIndexedResultMatcher<Integer> matcher);

    @Override
    WebListFilter setInitialHash(@Nullable String initialHash);

}
