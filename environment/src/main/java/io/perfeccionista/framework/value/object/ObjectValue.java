package io.perfeccionista.framework.value.object;

import io.perfeccionista.framework.value.Value;
import io.perfeccionista.framework.value.transformer.object.ObjectValueTransformer;
import org.jetbrains.annotations.NotNull;

import java.util.function.UnaryOperator;

public interface ObjectValue extends Value<Object> {

    @Override
    ObjectValue shouldMatch(Object actual);

    ObjectValue transformExpected(@NotNull UnaryOperator<Object> transformFunction);

    ObjectValue transformActual(@NotNull UnaryOperator<Object> transformFunction);

    ObjectValue addTransformer(@NotNull ObjectValueTransformer transformer);

    ObjectValue inverse();

}
