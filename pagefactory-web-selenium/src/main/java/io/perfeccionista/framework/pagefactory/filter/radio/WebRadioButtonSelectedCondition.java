package io.perfeccionista.framework.pagefactory.filter.radio;

public class WebRadioButtonSelectedCondition implements WebRadioButtonCondition {

    public WebRadioButtonSelectedCondition inverse() {

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
