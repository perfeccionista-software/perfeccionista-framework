package io.perfeccionista.framework.value.number;

import io.perfeccionista.framework.value.checker.NumberChecker;

import java.math.BigDecimal;

public class DefaultBigDecimalValue extends AbstractNumberValue<BigDecimal> {

    public DefaultBigDecimalValue(NumberChecker<BigDecimal> numberChecker) {
        super(numberChecker);
    }

}
