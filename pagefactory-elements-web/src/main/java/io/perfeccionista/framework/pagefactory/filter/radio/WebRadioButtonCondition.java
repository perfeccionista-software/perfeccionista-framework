package io.perfeccionista.framework.pagefactory.filter.radio;

import io.perfeccionista.framework.pagefactory.filter.Condition;

public interface WebRadioButtonCondition extends Condition {

    WebRadioButtonCondition and(WebRadioButtonCondition condition);

    WebRadioButtonCondition or(WebRadioButtonCondition condition);

}
