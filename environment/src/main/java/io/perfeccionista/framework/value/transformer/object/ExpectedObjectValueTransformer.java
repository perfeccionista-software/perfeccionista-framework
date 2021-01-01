package io.perfeccionista.framework.value.transformer.object;

import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface ExpectedObjectValueTransformer extends ObjectValueTransformer {

    default @NotNull Object transformActual(@NotNull Object actual) {
        return actual;
    }

}
