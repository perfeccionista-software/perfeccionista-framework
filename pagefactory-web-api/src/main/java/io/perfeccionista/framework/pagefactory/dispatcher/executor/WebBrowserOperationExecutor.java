package io.perfeccionista.framework.pagefactory.dispatcher.executor;

import io.perfeccionista.framework.pagefactory.operation.WebElementOperation;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperationResult;
import org.jetbrains.annotations.NotNull;

public interface WebBrowserOperationExecutor {

    /**
     * Используем враппер для Js операций
     * @param operation
     * @param <T>
     * @return
     */
    <T> WebElementOperationResult<T> executeWebElementOperation(@NotNull WebElementOperation<T> operation);

    Object executeScript(@NotNull String jsScript, Object... args);

    <T> T executeScript(@NotNull Class<T> returnType, @NotNull String jsScript, Object... args);

}
