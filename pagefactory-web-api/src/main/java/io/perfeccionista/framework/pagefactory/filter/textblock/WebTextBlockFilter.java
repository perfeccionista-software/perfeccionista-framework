package io.perfeccionista.framework.pagefactory.filter.textblock;

import io.perfeccionista.framework.matcher.result.WebMultipleIndexedResultMatcher;
import io.perfeccionista.framework.pagefactory.elements.WebLink;
import io.perfeccionista.framework.pagefactory.elements.WebTextList;
import io.perfeccionista.framework.pagefactory.extractor.textlist.WebTextListBlockValueExtractor;
import io.perfeccionista.framework.result.WebMultipleIndexedResult;
import io.perfeccionista.framework.result.WebSingleIndexedResult;
import io.perfeccionista.framework.pagefactory.filter.WebFilter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public interface WebTextBlockFilter extends WebFilter<WebTextList> {

    @NotNull WebSingleIndexedResult<String, WebTextList> extractOne();
    @NotNull WebMultipleIndexedResult<String, WebTextList> extractAll();
    @NotNull <T> WebSingleIndexedResult<T, WebTextList> extractOne(@NotNull WebTextListBlockValueExtractor<T> extractor);
    @NotNull <T> WebMultipleIndexedResult<T, WebTextList> extractAll(@NotNull WebTextListBlockValueExtractor<T> extractor);

    WebTextBlockFilter forSingle(@NotNull Consumer<WebLink> listBlockConsumer);
    WebTextBlockFilter forEach(@NotNull Consumer<WebLink> listBlockConsumer);
    WebTextBlockFilter forFirst(@NotNull Consumer<WebLink> listBlockConsumer);
    WebTextBlockFilter forLast(@NotNull Consumer<WebLink> listBlockConsumer);

    @Override
    WebTextBlockFilter should(@NotNull WebMultipleIndexedResultMatcher<Integer> matcher);

    @Override
    WebTextBlockFilter setInitialHash(@Nullable String initialHash);

}
