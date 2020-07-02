package io.perfeccionista.framework.value.string;

import io.perfeccionista.framework.value.Value;

import java.util.function.UnaryOperator;

// TODO: Метод toString вызывает метод toJson() и добавляет туда как expected,
//  так и actual значения, а так же результат сравнения
public interface StringValue extends Value<String> {

    StringValue transformExpected(UnaryOperator<String> transformFunction);

    StringValue transformActual(UnaryOperator<String> transformFunction);

    StringValue withoutProcessing();

    StringValue inverse();

}
