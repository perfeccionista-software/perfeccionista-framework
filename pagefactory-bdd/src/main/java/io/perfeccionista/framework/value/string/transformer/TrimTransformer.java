package io.perfeccionista.framework.value.string.transformer;

import io.perfeccionista.framework.value.string.StringTransformer;
import org.jetbrains.annotations.NotNull;

public class TrimTransformer implements StringTransformer {

    @Override
    public @NotNull String transformExpected(@NotNull String expected) {
        return expected.trim();
    }

    @Override
    public @NotNull String transformActual(@NotNull String actual) {
        return actual.trim();
    }

}
