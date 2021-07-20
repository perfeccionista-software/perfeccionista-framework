package io.perfeccionista.framework.pagefactory.filter.list;

import io.perfeccionista.framework.matcher.result.WebMultipleIndexedResultMatcher;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.extractor.list.WebListBlockValueExtractor;
import io.perfeccionista.framework.result.WebMultipleIndexedResult;
import io.perfeccionista.framework.result.WebSingleIndexedResult;
import io.perfeccionista.framework.pagefactory.filter.WebFilter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;
import java.util.function.Function;

public interface WebListFilter<T extends WebBlock> extends WebFilter<WebList<T>> {

    @NotNull <R> WebMultipleIndexedResult<R, WebList<T>> extractAll(@NotNull WebListBlockValueExtractor<R, T> extractor);
    @NotNull <R> WebMultipleIndexedResult<R, WebList<T>> extractAll(@NotNull Function<T, WebListBlockValueExtractor<R, T>> extractorFunction);
    @NotNull <R> WebSingleIndexedResult<R, WebList<T>> extractOne(@NotNull WebListBlockValueExtractor<R, T> extractor);
    @NotNull <R> WebSingleIndexedResult<R, WebList<T>> extractOne(@NotNull Function<T, WebListBlockValueExtractor<R, T>> extractorFunction);

    WebListFilter<T> forSingleBlock(@NotNull Consumer<T> listBlockConsumer);
    WebListFilter<T> forEachBlock(@NotNull Consumer<T> listBlockConsumer);
    WebListFilter<T> forFirstBlock(@NotNull Consumer<T> listBlockConsumer);
    WebListFilter<T> forLastBlock(@NotNull Consumer<T> listBlockConsumer);

    @Override
    WebListFilter<T> should(@NotNull WebMultipleIndexedResultMatcher<Integer> matcher);

    @Override
    WebListFilter<T> setInitialHash(@Nullable String initialHash);

}
