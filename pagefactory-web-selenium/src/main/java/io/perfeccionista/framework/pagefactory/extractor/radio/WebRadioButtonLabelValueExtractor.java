package io.perfeccionista.framework.pagefactory.extractor.radio;

import io.perfeccionista.framework.pagefactory.elements.WebRadioGroup;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.radio.WebRadioButtonFilter;
import io.perfeccionista.framework.pagefactory.filter.radio.WebRadioButtonFilterResult;

public class WebRadioButtonLabelValueExtractor implements WebRadioButtonValueExtractor<String> {

    @Override
    public MultipleResult<String> extractValues(WebRadioGroup element, WebRadioButtonFilterResult filter) {
        // Вся логика извлечения и фильтрации здесь

        return null;
    }

}
