package io.perfeccionista.framework.value;

import org.jetbrains.annotations.NotNull;

public interface Value<T> {

    @NotNull T get();

    boolean check(@NotNull T actual);

    // TODO: Implement
//    String shortDescription();

}
