package io.perfeccionista.framework.value.checker;

import io.perfeccionista.framework.value.transformer.ValueTransformer;
import org.jetbrains.annotations.NotNull;

public interface Checker<T> {

    boolean check();

    T getExpected();

    T getProcessedExpected();

    @NotNull T getActual();

    @NotNull T getProcessedActual();

    void setActual(@NotNull T actual);

    void addTransformer(@NotNull ValueTransformer<T> transformer);

    @NotNull String getComparisonDescription();

    @NotNull String getShortComparisonDescription();

}
