package io.perfeccionista.framework.value.transformer.number;

import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface ActualNumberValueTransformer<T extends Number> extends NumberValueTransformer<T> {

    default @NotNull T transformExpected(@NotNull T expected) {
        return expected;
    }

}
