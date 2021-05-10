package io.perfeccionista.framework.pagefactory.operation.type;

import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;
import org.jetbrains.annotations.NotNull;

public interface WebElementOperationType<T> {

    @NotNull InvocationName getInvocationName();

    @NotNull EndpointHandler<T> getEndpointHandler();

}

