package io.perfeccionista.framework.pagefactory.extractor.radio;

import io.perfeccionista.framework.pagefactory.elements.WebRadioGroup;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.radio.WebRadioButtonFilter;

public class WebRadioButtonEnabledMarkExtractor implements WebRadioButtonValueExtractor<Boolean> {

    @Override
    public MultipleResult<Boolean> extractValues(WebRadioGroup element, WebRadioButtonFilter filter) {
        // Вся логика извлечения и фильтрации здесь

        return null;
    }

}
