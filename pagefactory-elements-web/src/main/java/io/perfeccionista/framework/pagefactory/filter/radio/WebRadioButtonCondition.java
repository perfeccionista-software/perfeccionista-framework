package io.perfeccionista.framework.pagefactory.filter.radio;

public interface WebRadioButtonCondition {

    WebRadioButtonCondition and(WebRadioButtonCondition condition);

    WebRadioButtonCondition or(WebRadioButtonCondition condition);

}
