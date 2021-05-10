package io.perfeccionista.framework.pagefactory.dispatcher.executor;

import io.perfeccionista.framework.pagefactory.operation.MobileElementOperation;
import io.perfeccionista.framework.pagefactory.operation.MobileElementOperationResult;
import io.perfeccionista.framework.pagefactory.operation.MobilePageOperation;
import io.perfeccionista.framework.pagefactory.operation.MobilePageOperationResult;

public interface MobileDeviceOperationExecutor {

    /**
     * Используем враппер для Js операций
     * @param operation
     * @param <T>
     * @return
     */
    <T> MobileElementOperationResult<T> executeMobileElementOperation(MobileElementOperation<T> operation);

    <T> MobilePageOperationResult<T> executeMobilePageOperation(MobilePageOperation<T> operation);

}
