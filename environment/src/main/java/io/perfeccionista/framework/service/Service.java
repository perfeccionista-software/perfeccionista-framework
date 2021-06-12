package io.perfeccionista.framework.service;

import io.perfeccionista.framework.Environment;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Service {

    /**
     */
    void init(@NotNull Environment environment, @Nullable ServiceConfiguration configuration);

    /**
     * TODO: JavaDoc
     * Тут работает линтер, например
     */
    default void beforeTest() {
        // do nothing
    }

    /**
     * TODO: JavaDoc
     * Тут работает линтер, например
     */
    default void afterTest() {
        // do nothing
    }

}
