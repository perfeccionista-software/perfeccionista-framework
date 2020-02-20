package io.perfeccionista.framework.value.number.bigdecimal.checker;

import org.jetbrains.annotations.NotNull;
import io.perfeccionista.framework.value.number.NumberChecker;

import java.math.BigDecimal;

public class BigDecimalEqualsNumberChecker implements NumberChecker<BigDecimal> {

    @Override
    public boolean check(@NotNull BigDecimal expected, @NotNull BigDecimal actual) {
        return expected.equals(actual);
    }

}
