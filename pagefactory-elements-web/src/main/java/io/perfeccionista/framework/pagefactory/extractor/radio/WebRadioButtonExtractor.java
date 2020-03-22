package io.perfeccionista.framework.pagefactory.extractor.radio;

import io.perfeccionista.framework.pagefactory.elements.WebRadioButton;
import io.perfeccionista.framework.pagefactory.elements.WebRadioGroup;
import io.perfeccionista.framework.pagefactory.extractor.WebRadioButtonValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;

public class WebRadioButtonExtractor implements WebRadioButtonValueExtractor<WebRadioButton> {

    @Override
    public WebRadioButtonValueExtractor<WebRadioButton> setHash(String hash) {
        return null;
    }

    @Override
    public SingleResult<WebRadioButton> extractSingleValue(WebRadioGroup element, SingleResult<Integer> filterResult) {
        return null;
    }

    @Override
    public MultipleResult<WebRadioButton> extractMultipleValues(WebRadioGroup element, MultipleResult<Integer> filterResult) {
        return null;
    }
}
