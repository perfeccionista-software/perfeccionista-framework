package io.perfeccionista.framework.pagefactory.extractor.radio;

import io.perfeccionista.framework.pagefactory.elements.WebRadioGroup;
import io.perfeccionista.framework.pagefactory.extractor.WebRadioButtonValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;

public class WebRadioButtonSelectedExtractor implements WebRadioButtonValueExtractor<Boolean> {

    @Override
    public WebRadioButtonValueExtractor<Boolean> setHash(String hash) {
        return null;
    }

    @Override
    public SingleResult<Boolean> extractSingleValue(WebRadioGroup element, SingleResult<Integer> filterResult) {
        return null;
    }

    @Override
    public MultipleResult<Boolean> extractMultipleValues(WebRadioGroup element, MultipleResult<Integer> filterResult) {
        return null;
    }
}
