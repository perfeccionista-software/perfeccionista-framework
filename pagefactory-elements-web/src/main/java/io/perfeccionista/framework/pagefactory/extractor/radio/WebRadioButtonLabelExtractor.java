package io.perfeccionista.framework.pagefactory.extractor.radio;

import io.perfeccionista.framework.pagefactory.elements.WebRadioGroup;
import io.perfeccionista.framework.pagefactory.extractor.WebRadioButtonValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;

public class WebRadioButtonLabelExtractor implements WebRadioButtonValueExtractor<String> {

    @Override
    public WebRadioButtonValueExtractor<String> setHash(String hash) {
        return null;
    }

    @Override
    public SingleResult<String> extractSingleValue(WebRadioGroup element, SingleResult<Integer> filterResult) {
        return null;
    }

    @Override
    public MultipleResult<String> extractMultipleValues(WebRadioGroup element, MultipleResult<Integer> filterResult) {
        return null;
    }
}
