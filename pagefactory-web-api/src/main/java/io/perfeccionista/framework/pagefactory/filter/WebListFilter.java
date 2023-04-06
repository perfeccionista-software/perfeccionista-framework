package io.perfeccionista.framework.pagefactory.filter;

import io.perfeccionista.framework.matcher.result.WebMultipleIndexedResultMatcher;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.extractor.WebItemValueExtractor;
import io.perfeccionista.framework.result.WebMultipleIndexedResult;
import io.perfeccionista.framework.result.WebSingleIndexedResult;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;
import java.util.function.Function;

public interface WebListFilter<T extends WebBlock<?>> {

    @NotNull WebList<T> getElement();

    @NotNull <R> WebMultipleIndexedResult<R, WebList<T>> extractAll(@NotNull WebItemValueExtractor<R, T> extractor);
    @NotNull <R> WebMultipleIndexedResult<R, WebList<T>> extractAll(@NotNull Function<T, WebItemValueExtractor<R, T>> extractorFunction);
    @NotNull <R> WebSingleIndexedResult<R, WebList<T>> extractOne(@NotNull WebItemValueExtractor<R, T> extractor);
    @NotNull <R> WebSingleIndexedResult<R, WebList<T>> extractOne(@NotNull Function<T, WebItemValueExtractor<R, T>> extractorFunction);

    WebListFilter<T> forSingle(@NotNull Consumer<T> blockConsumer);
    WebListFilter<T> forEach(@NotNull Consumer<T> blockConsumer);
    WebListFilter<T> forFirst(@NotNull Consumer<T> blockConsumer);
    WebListFilter<T> forLast(@NotNull Consumer<T> blockConsumer);

    WebListFilter<T> should(WebMultipleIndexedResultMatcher<Integer> matcher);

    int size();

    @API(status = Status.INTERNAL)
    @NotNull FilterResult getFilterResult();

    @API(status = Status.INTERNAL)
    WebListFilter<T> setInitialHash(@Nullable String initialHash);

}
