package io.perfeccionista.framework.value.number;

import io.perfeccionista.framework.value.Value;
import org.jetbrains.annotations.Nullable;

import java.util.function.UnaryOperator;

public interface NumberValue<T extends Number> extends Value<T> {

    NumberValue<T> transformExpected(UnaryOperator<T> transformFunction);

    NumberValue<T> transformActual(UnaryOperator<T> transformFunction);

    NumberValue<T> withoutProcessing();

    boolean checkString(@Nullable String actual);


}
