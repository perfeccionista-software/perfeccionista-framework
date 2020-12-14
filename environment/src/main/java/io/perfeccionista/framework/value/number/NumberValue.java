package io.perfeccionista.framework.value.number;

import io.perfeccionista.framework.value.Value;
import io.perfeccionista.framework.value.transformer.number.NumberValueTransformer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.UnaryOperator;

public interface NumberValue<T extends Number> extends Value<T> {

    NumberValue<T> transformExpected(@NotNull UnaryOperator<T> transformFunction);

    NumberValue<T> transformActual(@NotNull UnaryOperator<T> transformFunction);

    NumberValue<T> addTransformer(@NotNull NumberValueTransformer<T> transformer);

    NumberValue<T> withoutProcessing();

    boolean checkString(@Nullable String actual);


}
