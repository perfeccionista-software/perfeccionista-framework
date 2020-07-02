package io.perfeccionista.framework.invocation.timeouts;

import org.jetbrains.annotations.NotNull;

import java.time.Duration;

/**
 * TODO: JavaDoc
 * Рекомендуется параметризовать этот класс enum-ом с ограниченным набором
 * ключей и использовать потом эти ключи в экшенах.
 */
public interface Timeouts {

    /**
     * Этот метод не должен возвращать null
     */
    @NotNull Duration getTimeout(@NotNull Class<? extends TimeoutsType> type);

}
