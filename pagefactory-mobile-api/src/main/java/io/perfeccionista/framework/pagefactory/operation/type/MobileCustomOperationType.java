package io.perfeccionista.framework.pagefactory.operation.type;

import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.invocation.runner.InvocationInfo.customOperationInvocation;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.EXECUTE_CUSTOM_OPERATION;

public class MobileCustomOperationType<T> implements MobileElementOperationType<T> {

    private final EndpointHandler<T> endpointHandler;

    private final InvocationInfo invocationInfo;

    private MobileCustomOperationType(EndpointHandler<T> endpointHandler) {
        this.endpointHandler = endpointHandler;
        this.invocationInfo = customOperationInvocation(EXECUTE_CUSTOM_OPERATION, endpointHandler.getClass().getCanonicalName());
    }

    public static <T> MobileCustomOperationType<T> of(@NotNull EndpointHandler<T> endpointHandler) {
        return new MobileCustomOperationType<>(endpointHandler);
    }

    @Override
    public @NotNull InvocationInfo getInvocationName() {
        return invocationInfo;
    }

    @Override
    public @NotNull EndpointHandler<T> getEndpointHandler() {
        return endpointHandler;
    }

}
