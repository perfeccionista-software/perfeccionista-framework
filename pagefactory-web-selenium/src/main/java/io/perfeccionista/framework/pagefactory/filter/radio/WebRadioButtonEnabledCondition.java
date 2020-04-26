package io.perfeccionista.framework.pagefactory.filter.radio;

public class WebRadioButtonEnabledCondition implements WebRadioButtonCondition {


    public WebRadioButtonEnabledCondition inverse() {

        return this;
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
