package io.perfeccionista.framework.pagefactory.filter.radio;

import io.perfeccionista.framework.matcher.result.WebMultipleIndexedResultMatcher;
import io.perfeccionista.framework.pagefactory.elements.WebRadioButton;
import io.perfeccionista.framework.pagefactory.elements.WebRadioGroup;
import io.perfeccionista.framework.pagefactory.extractor.radio.WebRadioButtonValueExtractor;
import io.perfeccionista.framework.result.WebMultipleIndexedResult;
import io.perfeccionista.framework.result.WebSingleIndexedResult;
import io.perfeccionista.framework.pagefactory.filter.WebFilter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public interface WebRadioGroupFilter extends WebFilter<WebRadioGroup> {

    @NotNull <T> WebSingleIndexedResult<T, WebRadioGroup> extractOne(@NotNull WebRadioButtonValueExtractor<T> extractor);
    @NotNull <T> WebMultipleIndexedResult<T, WebRadioGroup> extractAll(@NotNull WebRadioButtonValueExtractor<T> extractor);

    WebRadioGroupFilter forSingle(@NotNull Consumer<WebRadioButton> radioButtonConsumer);
    WebRadioGroupFilter forEach(@NotNull Consumer<WebRadioButton> radioButtonConsumer);
    WebRadioGroupFilter forFirst(@NotNull Consumer<WebRadioButton> radioButtonConsumer);
    WebRadioGroupFilter forLast(@NotNull Consumer<WebRadioButton> radioButtonConsumer);

    @Override
    WebRadioGroupFilter should(@NotNull WebMultipleIndexedResultMatcher<Integer> matcher);

    @Override
    WebRadioGroupFilter setInitialHash(@Nullable String initialHash);

}
