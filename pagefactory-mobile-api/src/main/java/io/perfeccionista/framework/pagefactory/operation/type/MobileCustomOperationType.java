package io.perfeccionista.framework.pagefactory.operation.type;

import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.invocation.runner.InvocationInfo.customOperationInvocation;

public class MobileCustomOperationType<T> implements MobileElementOperationType<T> {

    private final EndpointHandler<T> endpointHandler;

    private MobileCustomOperationType(EndpointHandler<T> endpointHandler) {
        this.endpointHandler = endpointHandler;
    }

    public static <T> MobileCustomOperationType<T> of(@NotNull EndpointHandler<T> endpointHandler) {
        return new MobileCustomOperationType<>(endpointHandler);
    }

    @Override
    public @NotNull InvocationInfo getInvocationName() {
        return customOperationInvocation(endpointHandler.getClass().getCanonicalName());
    }

    @Override
    public @NotNull EndpointHandler<T> getEndpointHandler() {
        return endpointHandler;
    }

}
