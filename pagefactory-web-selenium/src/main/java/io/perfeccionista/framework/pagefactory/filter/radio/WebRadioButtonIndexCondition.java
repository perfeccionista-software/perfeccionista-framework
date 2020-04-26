package io.perfeccionista.framework.pagefactory.filter.radio;

import io.perfeccionista.framework.value.number.NumberValue;

public class WebRadioButtonIndexCondition implements WebRadioButtonCondition {

    private final NumberValue<Integer> value;

    public WebRadioButtonIndexCondition(NumberValue<Integer> value) {
        this.value = value;
    }

    @Override
    public WebRadioButtonCondition and(WebRadioButtonCondition condition) {
        return null;
    }

    @Override
    public WebRadioButtonCondition or(WebRadioButtonCondition condition) {
        return null;
    }
}
