package io.perfeccionista.framework.pagefactory.filter;

import io.perfeccionista.framework.pagefactory.extractor.WebRadioButtonValueExtractor;
import io.perfeccionista.framework.value.number.NumberValue;

public class WebRadioButtonFilterResult implements FilterResult<WebRadioButtonFilter> {

    private final WebRadioButtonFilter filter;

    public WebRadioButtonFilterResult(WebRadioButtonFilter filter) {
        this.filter = filter;
    }

    public <T> SingleResult<T> extractOne(WebRadioButtonValueExtractor<T> extractor) {
        return null;
    }

    public <T> MultipleResult<T> extractAll(WebRadioButtonValueExtractor<T> extractor) {
        return null;
    }

    public WebRadioButtonFilterResult shouldHaveSize(NumberValue<Integer> integerValue) {
        return null;
    }

}
