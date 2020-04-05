package io.perfeccionista.framework.value.number.integervalue;

import io.perfeccionista.framework.value.number.AbstractNumberValue;
import io.perfeccionista.framework.value.number.NumberChecker;

public class DefaultIntegerValue extends AbstractNumberValue<Integer> {

    public DefaultIntegerValue(NumberChecker<Integer> numberChecker) {
        super(numberChecker);
    }

}
