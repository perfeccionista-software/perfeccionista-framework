package io.perfeccionista.framework.pagefactory.filter.radio;

import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;

public class WebRadioButtonLabelCondition implements WebRadioButtonCondition {

    private final StringValue stringValue;
    private final NumberValue<? extends Number> numberValue;

    public WebRadioButtonLabelCondition(StringValue stringValue) {
        this.stringValue = stringValue;
        this.numberValue = null;
    }

    public WebRadioButtonLabelCondition(NumberValue<? extends Number> numberValue) {
        this.stringValue = null;
        this.numberValue = numberValue;
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
