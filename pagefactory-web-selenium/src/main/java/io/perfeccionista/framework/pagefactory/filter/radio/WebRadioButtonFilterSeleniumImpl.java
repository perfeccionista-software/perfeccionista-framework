package io.perfeccionista.framework.pagefactory.filter.radio;

import io.perfeccionista.framework.pagefactory.elements.WebRadioGroup;
import io.perfeccionista.framework.pagefactory.extractor.radio.WebRadioButtonIndexExtractor;
import io.perfeccionista.framework.pagefactory.extractor.radio.WebRadioButtonValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;
import io.perfeccionista.framework.pagefactory.filter.WebFilterResult;
import io.perfeccionista.framework.value.number.NumberValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class WebRadioButtonFilterSeleniumImpl implements WebRadioButtonFilter {

    private final WebRadioGroup element;
    private final WebRadioButtonFilterBuilder filter;

    public WebRadioButtonFilterSeleniumImpl(WebRadioGroup element, WebRadioButtonFilterBuilder filter) {
        this.element = element;
        this.filter = filter;
    }


    @Override
    public WebRadioButtonFilter setInitialHash(@Nullable String initialHash) {
        return null;
    }

    @Override
    public @NotNull WebFilterResult getResult() {
        return null;
    }

    public <T> SingleResult<T> extractOne(WebRadioButtonValueExtractor<T> extractor) {
        return extractor
                .extractValues(element, filter.build(element))
                .singleResult();
    }

    public <T> MultipleResult<T> extractAll(WebRadioButtonValueExtractor<T> extractor) {
        return extractor
                .extractValues(element, filter.build(element));
    }

    public WebRadioButtonFilter shouldHaveSize(NumberValue<Integer> expectedSize) {
        new WebRadioButtonIndexExtractor()
                .extractValues(element, filter.build(element))
                .shouldHaveSize(expectedSize);
        return this;
    }

}
