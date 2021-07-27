package io.perfeccionista.framework.pagefactory.filter.block;

import io.perfeccionista.framework.matcher.result.WebMultipleIndexedResultMatcher;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.extractor.list.WebBlockValueExtractor;
import io.perfeccionista.framework.result.WebMultipleIndexedResult;
import io.perfeccionista.framework.result.WebSingleIndexedResult;
import io.perfeccionista.framework.pagefactory.filter.WebFilter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;
import java.util.function.Function;

public interface WebBlockFilter<T extends WebBlock> extends WebFilter<WebList<T>> {

    @NotNull <R> WebMultipleIndexedResult<R, WebList<T>> extractAll(@NotNull WebBlockValueExtractor<R, T> extractor);
    @NotNull <R> WebMultipleIndexedResult<R, WebList<T>> extractAll(@NotNull Function<T, WebBlockValueExtractor<R, T>> extractorFunction);
    @NotNull <R> WebSingleIndexedResult<R, WebList<T>> extractOne(@NotNull WebBlockValueExtractor<R, T> extractor);
    @NotNull <R> WebSingleIndexedResult<R, WebList<T>> extractOne(@NotNull Function<T, WebBlockValueExtractor<R, T>> extractorFunction);

    WebBlockFilter<T> forSingle(@NotNull Consumer<T> blockConsumer);
    WebBlockFilter<T> forEach(@NotNull Consumer<T> blockConsumer);
    WebBlockFilter<T> forFirst(@NotNull Consumer<T> blockConsumer);
    WebBlockFilter<T> forLast(@NotNull Consumer<T> blockConsumer);

    @Override
    WebBlockFilter<T> should(@NotNull WebMultipleIndexedResultMatcher<Integer> matcher);

    @Override
    WebBlockFilter<T> setInitialHash(@Nullable String initialHash);

}