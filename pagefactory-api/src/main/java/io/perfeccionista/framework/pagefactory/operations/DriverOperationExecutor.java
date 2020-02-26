package io.perfeccionista.framework.pagefactory.operations;

public interface DriverOperationExecutor {

    /**
     * Используем враппер для Js операций
     * @param operation
     * @param <T>
     * @return
     */
    <T> T executeOperation(JsOperation<T> operation);

}
