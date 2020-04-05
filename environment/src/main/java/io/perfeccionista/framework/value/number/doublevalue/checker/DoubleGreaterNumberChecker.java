package io.perfeccionista.framework.value.number.doublevalue.checker;

import io.perfeccionista.framework.value.number.AbstractNumberChecker;
import org.jetbrains.annotations.NotNull;

public class DoubleGreaterNumberChecker extends AbstractNumberChecker<Double> {

    private Double expectedIntValue;

    public DoubleGreaterNumberChecker(Double expectedIntValue) {
        this.expectedIntValue = expectedIntValue;
    }

    @Override
    public Double getExpected() {
        return expectedIntValue;
    }

    @Override
    public Double getProcessedExpected() {
        return applyTransformers(expectedIntValue);
    }

    @Override
    public boolean check(@NotNull Double actual) {
        return actual.compareTo(getProcessedExpected()) > 0;
    }

}