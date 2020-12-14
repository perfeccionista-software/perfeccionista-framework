package io.perfeccionista.framework.value.transformer.string;

import org.jetbrains.annotations.NotNull;

public class TrimTransformer implements StringValueTransformer {

    @Override
    public @NotNull String transformExpected(@NotNull String expected) {
        return expected.trim();
    }

    @Override
    public @NotNull String transformActual(@NotNull String actual) {
        return actual.trim();
    }

}
