package io.perfeccionista.framework.value.checker;

import org.jetbrains.annotations.NotNull;

public interface ObjectChecker extends Checker<Object> {

    @Override
    @NotNull Object getExpected();

    @Override
    @NotNull Object getProcessedExpected();

}
