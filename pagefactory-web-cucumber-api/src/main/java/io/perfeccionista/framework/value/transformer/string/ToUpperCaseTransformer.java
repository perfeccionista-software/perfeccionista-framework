package io.perfeccionista.framework.value.transformer.string;

import org.jetbrains.annotations.NotNull;

public class ToUpperCaseTransformer implements ExpectedStringValueTransformer {

    @Override
    public @NotNull String transformExpected(@NotNull String expected) {
        return expected.toUpperCase();
    }

}
