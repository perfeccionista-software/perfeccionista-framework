package io.perfeccionista.framework.pagefactory.operations;

public interface DriverOperationExecutor {

    /**
     * Используем враппер для Js операций
     * @param operation
     * @param <T>
     * @return
     */
    <T> OperationResult<T> executeOperation(DriverJsOperation<T> operation);

}
