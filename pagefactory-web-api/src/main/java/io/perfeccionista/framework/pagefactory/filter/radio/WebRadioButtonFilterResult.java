package io.perfeccionista.framework.pagefactory.filter.radio;

import io.perfeccionista.framework.pagefactory.extractor.radio.WebRadioButtonValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.FilterResult;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;
import io.perfeccionista.framework.value.number.NumberValue;

public interface WebRadioButtonFilterResult extends FilterResult {

    <T> SingleResult<T> extractOne(WebRadioButtonValueExtractor<T> extractor);

    <T> MultipleResult<T> extractAll(WebRadioButtonValueExtractor<T> extractor);

    WebRadioButtonFilterResult shouldHaveSize(NumberValue<Integer> integerValue);

}
