package io.perfeccionista.framework.service;

import org.jetbrains.annotations.NotNull;

public interface Service {

    /**
     */
    void init(@NotNull ServiceConfiguration configuration);

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
