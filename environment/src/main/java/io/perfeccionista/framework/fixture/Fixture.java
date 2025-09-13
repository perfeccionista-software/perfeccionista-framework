package io.perfeccionista.framework.fixture;

import org.jetbrains.annotations.NotNull;

/**
 * Формируем результат и при ошибке при необходимости запускаем обработчик ошибок:
 * <pre>
 * {@code
 * FixtureTearDownResult.failure(exception)
 *     .ifException(exception -> throw exception);
 * }
 *
 * </pre>
 */

public interface Fixture<S, T> {

    @NotNull FixtureSetUpResult<S> setUp();

    @NotNull FixtureTearDownResult<T> tearDown();

}
