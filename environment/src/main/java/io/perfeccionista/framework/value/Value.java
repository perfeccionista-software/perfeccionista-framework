package io.perfeccionista.framework.value;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Value<T> {

    @NotNull T get();

    boolean check(@Nullable T actual);

    // TODO: Implement
//    String shortDescription();

}
