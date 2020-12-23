package io.perfeccionista.framework.value.transformer.string;

import org.jetbrains.annotations.NotNull;

public class RemoveLineBreaksTransformer implements ActualStringValueTransformer {

    @Override
    public @NotNull String transformActual(@NotNull String actual) {
        return actual.replace("\n", "");
    }

}
