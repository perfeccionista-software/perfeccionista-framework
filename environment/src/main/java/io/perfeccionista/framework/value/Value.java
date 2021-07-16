package io.perfeccionista.framework.value;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Value<T> {

    @Nullable T get();

    boolean check(@Nullable T actual);

    Value<T> shouldMatch(T actual);

    @NotNull String getShortDescription();

}
