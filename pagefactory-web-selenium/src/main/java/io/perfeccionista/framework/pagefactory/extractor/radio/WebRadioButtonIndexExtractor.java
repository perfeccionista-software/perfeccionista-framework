package io.perfeccionista.framework.pagefactory.extractor.radio;

import io.perfeccionista.framework.pagefactory.elements.WebRadioGroup;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.radio.WebRadioButtonFilter;

public class WebRadioButtonIndexExtractor implements WebRadioButtonValueExtractor<Integer> {

    @Override
    public MultipleResult<Integer> extractValues(WebRadioGroup element, WebRadioButtonFilter filter) {
        // Вся логика извлечения и фильтрации здесь

        return null;
    }

}
