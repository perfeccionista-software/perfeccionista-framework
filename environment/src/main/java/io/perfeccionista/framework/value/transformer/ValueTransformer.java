package io.perfeccionista.framework.value.transformer;

import org.jetbrains.annotations.NotNull;

public interface ValueTransformer<T> {

    @NotNull T transformExpected(@NotNull T expected);

    @NotNull T transformActual(@NotNull T actual);

}
