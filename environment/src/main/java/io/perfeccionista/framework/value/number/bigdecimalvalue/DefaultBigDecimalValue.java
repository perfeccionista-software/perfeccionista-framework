package io.perfeccionista.framework.value.number.bigdecimalvalue;

import io.perfeccionista.framework.value.number.AbstractNumberValue;
import io.perfeccionista.framework.value.number.NumberChecker;

import java.math.BigDecimal;

public class DefaultBigDecimalValue extends AbstractNumberValue<BigDecimal> {

    public DefaultBigDecimalValue(NumberChecker<BigDecimal> numberChecker) {
        super(numberChecker);
    }

}
