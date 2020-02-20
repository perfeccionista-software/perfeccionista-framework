package io.perfeccionista.framework.pagefactory.driver;

import io.perfeccionista.framework.exceptions.mapper.ExceptionMapper;
import io.perfeccionista.framework.pagefactory.js.JsRepository;
import io.perfeccionista.framework.pagefactory.operations.DriverOperationExecutor;

/**
 * На основе конфигурации создает/закрывает и конфигурирует экземпляр браузера
 *
 *
 * Капабилитис, таймауты, пути, прокси и т.п.
 *
 */
public interface DriverInstance<T> {

    T getDriver();

    JsRepository getJsRepository();

    DriverOperationExecutor getDriverOperationExecutor();

    ExceptionMapper getExceptionMapper(Class<? extends ExceptionMapper> exceptionMapperClass);

}
