package io.perfeccionista.framework.value;

import org.jetbrains.annotations.NotNull;

public interface Checker<T> {

    boolean check(@NotNull T expected, @NotNull T actual);

    default boolean isProcessExpectedStatement() {
        return true;
    }

}
