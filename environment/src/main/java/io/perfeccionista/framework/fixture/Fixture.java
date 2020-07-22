package io.perfeccionista.framework.fixture;

import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public interface Fixture<T> {

    @NotNull
    Optional<T> execute();

    boolean revert();

}
