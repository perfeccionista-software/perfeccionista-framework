package io.perfeccionista.framework.value.transformer.number;

import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface ExpectedNumberValueTransformer<T extends Number> extends NumberValueTransformer<T> {

    default @NotNull T transformActual(@NotNull T actual) {
        return actual;
    }

}