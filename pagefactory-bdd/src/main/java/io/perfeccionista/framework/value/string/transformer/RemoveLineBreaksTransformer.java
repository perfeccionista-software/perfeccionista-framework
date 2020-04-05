package io.perfeccionista.framework.value.string.transformer;

import org.jetbrains.annotations.NotNull;
import io.perfeccionista.framework.value.string.StringTransformer;

public class RemoveLineBreaksTransformer implements StringTransformer {

    @Override
    public @NotNull String transformExpected(@NotNull String expected) {
        return expected;
    }

    @Override
    public @NotNull String transformActual(@NotNull String actual) {
        return actual.replace("\n", "");
    }

}
