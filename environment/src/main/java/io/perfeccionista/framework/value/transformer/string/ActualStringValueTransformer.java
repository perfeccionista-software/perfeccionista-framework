package io.perfeccionista.framework.value.transformer.string;

import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface ActualStringValueTransformer extends StringValueTransformer {

    default @NotNull String transformExpected(@NotNull String expected) {
        return expected;
    }

}
