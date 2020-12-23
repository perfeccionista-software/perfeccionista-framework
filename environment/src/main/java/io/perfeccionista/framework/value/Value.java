package io.perfeccionista.framework.value;

import org.jetbrains.annotations.Nullable;

public interface Value<T> {

    @Nullable T get();

    boolean check(@Nullable T actual);

}
