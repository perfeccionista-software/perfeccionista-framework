package io.perfeccionista.framework.pagefactory.filter.radio;

import io.perfeccionista.framework.matcher.result.WebMultipleIndexedResultMatcher;
import io.perfeccionista.framework.pagefactory.elements.WebRadioGroup;
import io.perfeccionista.framework.pagefactory.extractor.radio.WebRadioButtonValueExtractor;
import io.perfeccionista.framework.result.WebMultipleIndexedResult;
import io.perfeccionista.framework.result.WebSingleIndexedResult;
import io.perfeccionista.framework.pagefactory.filter.WebFilter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface WebRadioGroupFilter extends WebFilter<WebRadioGroup> {

    @NotNull <T> WebSingleIndexedResult<T, WebRadioGroup> extractOne(@NotNull WebRadioButtonValueExtractor<T> extractor);

    @NotNull <T> WebMultipleIndexedResult<T, WebRadioGroup> extractAll(@NotNull WebRadioButtonValueExtractor<T> extractor);

    @Override
    WebRadioGroupFilter should(@NotNull WebMultipleIndexedResultMatcher<Integer> matcher);

    @Override
    WebRadioGroupFilter setInitialHash(@Nullable String initialHash);

}
