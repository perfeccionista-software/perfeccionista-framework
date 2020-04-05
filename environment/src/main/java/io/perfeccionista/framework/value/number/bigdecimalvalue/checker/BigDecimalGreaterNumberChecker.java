package io.perfeccionista.framework.value.number.bigdecimalvalue.checker;

import io.perfeccionista.framework.value.number.AbstractNumberChecker;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

public class BigDecimalGreaterNumberChecker extends AbstractNumberChecker<BigDecimal> {

    private BigDecimal expectedIntValue;

    public BigDecimalGreaterNumberChecker(BigDecimal expectedIntValue) {
        this.expectedIntValue = expectedIntValue;
    }

    @Override
    public BigDecimal getExpected() {
        return expectedIntValue;
    }

    @Override
    public BigDecimal getProcessedExpected() {
        return applyTransformers(expectedIntValue);
    }

    @Override
    public boolean check(@NotNull BigDecimal actual) {
        return actual.compareTo(getProcessedExpected()) > 0;
    }

}
