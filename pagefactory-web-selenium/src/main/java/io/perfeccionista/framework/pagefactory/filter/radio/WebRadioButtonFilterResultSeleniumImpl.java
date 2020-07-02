package io.perfeccionista.framework.pagefactory.filter.radio;

import io.perfeccionista.framework.pagefactory.elements.WebRadioGroup;
import io.perfeccionista.framework.pagefactory.extractor.radio.WebRadioButtonIndexExtractor;
import io.perfeccionista.framework.pagefactory.extractor.radio.WebRadioButtonValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;
import io.perfeccionista.framework.value.number.NumberValue;

public class WebRadioButtonFilterResultSeleniumImpl implements WebRadioButtonFilterResult {

    private final WebRadioGroup element;
    private final WebRadioButtonFilter filter;

    public WebRadioButtonFilterResultSeleniumImpl(WebRadioGroup element, WebRadioButtonFilter filter) {
        this.element = element;
        this.filter = filter;
    }

    public <T> SingleResult<T> extractOne(WebRadioButtonValueExtractor<T> extractor) {
        return extractor
                .extractValues(element, filter.filter(element))
                .singleResult();
    }

    public <T> MultipleResult<T> extractAll(WebRadioButtonValueExtractor<T> extractor) {
        return extractor
                .extractValues(element, filter.filter(element));
    }

    public WebRadioButtonFilterResult shouldHaveSize(NumberValue<Integer> integerValue) {
        new WebRadioButtonIndexExtractor()
                .extractValues(element, filter.filter(element))
                .shouldHaveSize(integerValue);
        return this;
    }

}
