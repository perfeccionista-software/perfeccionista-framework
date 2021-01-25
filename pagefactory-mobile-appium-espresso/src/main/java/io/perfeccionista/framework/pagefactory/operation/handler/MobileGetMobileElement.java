package io.perfeccionista.framework.pagefactory.operation.handler;

import io.appium.java_client.MobileElement;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;
import io.perfeccionista.framework.pagefactory.operation.type.MobileElementOperationType;
import org.jetbrains.annotations.NotNull;

public class MobileGetMobileElement implements MobileElementOperationType<MobileElement> {

    @Override
    public @NotNull InvocationName getInvocationName() {
        return null;
    }

    @Override
    public @NotNull EndpointHandler<MobileElement> getEndpointHandler() {
        return null;
    }

}

