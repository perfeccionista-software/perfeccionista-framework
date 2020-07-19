package io.perfeccionista.framework.pagefactory.filter.radio;

import io.perfeccionista.framework.pagefactory.extractor.radio.WebRadioButtonValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.FilterResult;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;
import io.perfeccionista.framework.value.number.NumberValue;
import org.jetbrains.annotations.NotNull;

public interface WebRadioButtonFilterResult extends FilterResult {

    @NotNull <T> SingleResult<T> extractOne(@NotNull WebRadioButtonValueExtractor<T> extractor);

    @NotNull <T> MultipleResult<T> extractAll(@NotNull WebRadioButtonValueExtractor<T> extractor);

    WebRadioButtonFilterResult shouldHaveSize(@NotNull NumberValue<Integer> expectedSize);

}
