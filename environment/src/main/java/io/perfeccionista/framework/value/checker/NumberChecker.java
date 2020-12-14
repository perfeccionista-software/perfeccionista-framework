package io.perfeccionista.framework.value.checker;

import org.jetbrains.annotations.NotNull;

public interface NumberChecker<T extends Number> extends Checker<T> {

    @Override
    @NotNull T getExpected();

    @Override
    @NotNull T getProcessedExpected();

}
