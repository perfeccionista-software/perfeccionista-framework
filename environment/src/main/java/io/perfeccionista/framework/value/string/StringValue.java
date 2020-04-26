package io.perfeccionista.framework.value.string;

import io.perfeccionista.framework.value.Value;

import java.util.function.UnaryOperator;

public interface StringValue extends Value<String> {

    StringValue transformExpected(UnaryOperator<String> transformFunction);

    StringValue transformActual(UnaryOperator<String> transformFunction);

    StringValue withoutProcessing();

    StringValue inverse();

}
