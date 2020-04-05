package io.perfeccionista.framework.value.number.integervalue.checker;

import io.perfeccionista.framework.value.number.AbstractNumberChecker;
import org.jetbrains.annotations.NotNull;

public class IntegerEqualsNumberChecker extends AbstractNumberChecker<Integer> {

    private int expectedIntValue;

    public IntegerEqualsNumberChecker(int expectedIntValue) {
        this.expectedIntValue = expectedIntValue;
    }

    @Override
    public Integer getExpected() {
        return expectedIntValue;
    }

    @Override
    public Integer getProcessedExpected() {
        return applyTransformers(expectedIntValue);
    }

    @Override
    public boolean check(@NotNull Integer actual) {
        return actual.equals(getProcessedExpected());
    }

}
