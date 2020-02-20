package io.perfeccionista.framework.value.number;

import org.jetbrains.annotations.NotNull;
import io.perfeccionista.framework.value.Checker;

public interface NumberChecker<T extends Number> extends Checker<T> {

    @Override
    boolean check(@NotNull T expected, @NotNull T actual);

}
