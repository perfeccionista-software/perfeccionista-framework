package io.perfeccionista.framework.value.number.doublevalue;

import io.perfeccionista.framework.value.number.AbstractNumberValue;
import io.perfeccionista.framework.value.checker.NumberChecker;
import org.jetbrains.annotations.NotNull;

@Deprecated
public class RuDoubleValue extends AbstractNumberValue<Double> {

    public RuDoubleValue(NumberChecker<Double> numberChecker) {
        super(numberChecker);
    }

    @Override
    public boolean checkString(@NotNull String actual) {
        // TODO: Implement
        return false;
    }
}
