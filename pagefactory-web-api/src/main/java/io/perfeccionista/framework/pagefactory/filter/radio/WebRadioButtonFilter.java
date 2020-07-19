package io.perfeccionista.framework.pagefactory.filter.radio;

import io.perfeccionista.framework.pagefactory.extractor.radio.WebRadioButtonValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;
import io.perfeccionista.framework.pagefactory.filter.WebFilter;
import io.perfeccionista.framework.value.number.NumberValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface WebRadioButtonFilter extends WebFilter {

    @Override
    WebRadioButtonFilter setInitialHash(@Nullable String initialHash);

    @Override
    WebRadioButtonFilter shouldHaveSize(@NotNull NumberValue<Integer> expectedSize);

    @NotNull <T> SingleResult<T> extractOne(@NotNull WebRadioButtonValueExtractor<T> extractor);

    @NotNull <T> MultipleResult<T> extractAll(@NotNull WebRadioButtonValueExtractor<T> extractor);

}
