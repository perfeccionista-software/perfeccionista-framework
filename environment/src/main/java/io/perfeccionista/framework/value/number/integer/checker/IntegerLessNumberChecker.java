package io.perfeccionista.framework.value.number.integer.checker;

import org.jetbrains.annotations.NotNull;
import io.perfeccionista.framework.value.number.NumberChecker;

public class IntegerLessNumberChecker implements NumberChecker<Integer> {

    @Override
    public boolean check(@NotNull Integer expected, @NotNull Integer actual) {
        return expected.compareTo(actual) < 0;
    }

}
