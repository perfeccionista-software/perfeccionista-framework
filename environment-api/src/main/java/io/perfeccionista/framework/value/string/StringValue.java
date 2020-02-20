package io.perfeccionista.framework.value.string;

import org.jetbrains.annotations.Nullable;
import io.perfeccionista.framework.value.Value;

public interface StringValue extends Value<String> {

    void setStringTransformers(@Nullable StringTransformer... stringTransformers);

}
