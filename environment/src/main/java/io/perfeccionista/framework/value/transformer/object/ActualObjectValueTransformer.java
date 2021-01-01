package io.perfeccionista.framework.value.transformer.object;

import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface ActualObjectValueTransformer extends ObjectValueTransformer {

    default @NotNull Object transformExpected(@NotNull Object expected) {
        return expected;
    }

}
