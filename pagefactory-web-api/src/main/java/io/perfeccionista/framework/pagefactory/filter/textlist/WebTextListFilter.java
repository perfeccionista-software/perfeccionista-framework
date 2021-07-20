package io.perfeccionista.framework.pagefactory.filter.textlist;

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

public interface WebTextListFilter extends WebFilter<WebTextList> {

    @NotNull WebSingleIndexedResult<String, WebTextList> extractOne();
    @NotNull WebMultipleIndexedResult<String, WebTextList> extractAll();
    @NotNull <T> WebSingleIndexedResult<T, WebTextList> extractOne(@NotNull WebTextListBlockValueExtractor<T> extractor);
    @NotNull <T> WebMultipleIndexedResult<T, WebTextList> extractAll(@NotNull WebTextListBlockValueExtractor<T> extractor);

    WebTextListFilter forSingleBlock(@NotNull Consumer<WebLink> listBlockConsumer);
    WebTextListFilter forEachBlock(@NotNull Consumer<WebLink> listBlockConsumer);
    WebTextListFilter forFirstBlock(@NotNull Consumer<WebLink> listBlockConsumer);
    WebTextListFilter forLastBlock(@NotNull Consumer<WebLink> listBlockConsumer);

    @Override
    WebTextListFilter should(@NotNull WebMultipleIndexedResultMatcher<Integer> matcher);

    @Override
    WebTextListFilter setInitialHash(@Nullable String initialHash);

}
