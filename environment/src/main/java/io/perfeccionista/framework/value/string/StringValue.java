package io.perfeccionista.framework.value.string;

import io.perfeccionista.framework.value.Value;
import io.perfeccionista.framework.value.transformer.string.StringValueTransformer;
import org.jetbrains.annotations.NotNull;

import java.util.function.UnaryOperator;

// TODO: Метод toString вызывает метод toJson() и добавляет туда как expected,
//  так и actual значения, а так же результат сравнения
public interface StringValue extends Value<String> {

    @Override
    @NotNull String get();

    @Override
    StringValue shouldMatch(String actual);

    StringValue transformExpected(@NotNull UnaryOperator<String> transformFunction);

    StringValue transformActual(@NotNull UnaryOperator<String> transformFunction);

    StringValue addTransformer(@NotNull StringValueTransformer transformer);

    StringValue withoutProcessing();

    StringValue inverse();

}
