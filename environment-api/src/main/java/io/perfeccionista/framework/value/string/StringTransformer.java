package io.perfeccionista.framework.value.string;

import org.jetbrains.annotations.NotNull;

public interface StringTransformer {

    @NotNull String transformExpected(@NotNull String expected);

    @NotNull String transformActual(@NotNull String actual);

}
