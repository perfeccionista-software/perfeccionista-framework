package io.perfeccionista.framework.pagefactory.browser.executor;

import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import io.perfeccionista.framework.pagefactory.operation.JsOperationResult;

public interface OperationExecutor {

    /**
     * Используем враппер для Js операций
     * @param operation
     * @param <T>
     * @return
     */
    <T> JsOperationResult<T> executeOperation(JsOperation<T> operation);

}
