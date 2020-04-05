package io.perfeccionista.framework.value.number.doublevalue;

import io.perfeccionista.framework.value.number.AbstractNumberValue;
import io.perfeccionista.framework.value.number.NumberChecker;

public class DefaultDoubleValue extends AbstractNumberValue<Double> {

    public DefaultDoubleValue(NumberChecker<Double> numberChecker) {
        super(numberChecker);
    }

}
