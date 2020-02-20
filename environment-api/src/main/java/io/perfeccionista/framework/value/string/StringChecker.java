package io.perfeccionista.framework.value.string;

import org.jetbrains.annotations.NotNull;
import io.perfeccionista.framework.value.Checker;

public interface StringChecker extends Checker<String> {

    @Override
    boolean check(@NotNull String expected, @NotNull String actual);

}
