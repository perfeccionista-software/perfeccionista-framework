package io.perfeccionista.framework.value.number;

import io.perfeccionista.framework.value.checker.NumberChecker;

public class DefaultIntegerValue extends AbstractNumberValue<Integer> {

    public DefaultIntegerValue(NumberChecker<Integer> numberChecker) {
        super(numberChecker);
    }

}
