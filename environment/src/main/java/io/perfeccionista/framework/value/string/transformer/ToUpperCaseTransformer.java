package io.perfeccionista.framework.value.string.transformer;

import org.jetbrains.annotations.NotNull;
import io.perfeccionista.framework.value.string.StringTransformer;

public class ToUpperCaseTransformer implements StringTransformer {

    @Override
    public @NotNull String transformExpected(@NotNull String expected) {
        return expected.toUpperCase();
    }

    @Override
    public @NotNull String transformActual(@NotNull String actual) {
        return actual;
    }

}
