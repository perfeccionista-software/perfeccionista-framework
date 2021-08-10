package io.perfeccionista.framework.value.checker;

import io.perfeccionista.framework.value.transformer.ValueTransformer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Checker<T> {

    boolean check();

    T getExpected();

    T getProcessedExpected();

    @Nullable T getActual();

    @Nullable T getProcessedActual();

    void setActual(@NotNull T actual);

    void addTransformer(@NotNull ValueTransformer<T> transformer);

    @NotNull String getComparisonDescription();

    @NotNull String getShortComparisonDescription();

}
