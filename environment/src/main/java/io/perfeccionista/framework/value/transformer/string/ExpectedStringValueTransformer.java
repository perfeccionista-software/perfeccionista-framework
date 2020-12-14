package io.perfeccionista.framework.value.transformer.string;

import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface ExpectedStringValueTransformer extends StringValueTransformer {

    default @NotNull String transformActual(@NotNull String actual) {
        return actual;
    }

}
