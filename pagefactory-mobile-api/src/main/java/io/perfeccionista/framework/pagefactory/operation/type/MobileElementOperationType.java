package io.perfeccionista.framework.pagefactory.operation.type;

import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;
import org.jetbrains.annotations.NotNull;

public interface MobileElementOperationType<T> {

    @NotNull InvocationInfo getInvocationName();

    @NotNull EndpointHandler<T> getEndpointHandler();

}
