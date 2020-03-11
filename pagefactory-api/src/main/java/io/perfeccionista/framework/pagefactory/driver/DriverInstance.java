package io.perfeccionista.framework.pagefactory.driver;

import io.perfeccionista.framework.exceptions.mapper.ExceptionMapper;

/**
 * На основе конфигурации создает/закрывает и конфигурирует экземпляр браузера
 *
 *
 * Капабилитис, таймауты, пути, прокси и т.п.
 *
 */
public interface DriverInstance<T> {

    T getDriver();

    ExceptionMapper getExceptionMapper(Class<? extends ExceptionMapper> exceptionMapperClass);

}
