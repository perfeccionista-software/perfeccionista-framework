package io.perfeccionista.framework.pagefactory.browser.executor;

import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import io.perfeccionista.framework.pagefactory.operation.JsOperationResult;
import org.jetbrains.annotations.NotNull;

public interface OperationExecutor {

    /**
     * Используем враппер для Js операций
     * @param operation
     * @param <T>
     * @return
     */
    <T> JsOperationResult<T> executeOperation(JsOperation<T> operation);

    Object executeScript(@NotNull String jsScript, Object... args);

    <T> T executeScript(@NotNull Class<T> returnType, @NotNull String jsScript, Object... args);

}
