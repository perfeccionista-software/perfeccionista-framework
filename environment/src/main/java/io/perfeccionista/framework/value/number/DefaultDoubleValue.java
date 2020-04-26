package io.perfeccionista.framework.value.number;

import io.perfeccionista.framework.value.checker.NumberChecker;

public class DefaultDoubleValue extends AbstractNumberValue<Double> {

    public DefaultDoubleValue(NumberChecker<Double> numberChecker) {
        super(numberChecker);
    }

}
