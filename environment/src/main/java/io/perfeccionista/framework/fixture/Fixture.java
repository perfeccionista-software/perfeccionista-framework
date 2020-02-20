package io.perfeccionista.framework.fixture;

import java.util.Optional;

public interface Fixture<T> {

    Optional<T> execute();

    boolean revert();

}
