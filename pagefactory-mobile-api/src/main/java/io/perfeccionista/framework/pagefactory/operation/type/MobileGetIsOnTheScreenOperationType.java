package io.perfeccionista.framework.pagefactory.operation.type;

import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElementBase;
import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;

import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.IS_ON_THE_SCREEN_METHOD;
import static org.junit.platform.commons.util.ReflectionUtils.getDeclaredConstructor;
import static org.junit.platform.commons.util.ReflectionUtils.newInstance;

public class MobileGetIsOnTheScreenOperationType implements MobileElementOperationType<Boolean> {

    private final MobileChildElementBase element;

    private MobileGetIsOnTheScreenOperationType(MobileChildElementBase element) {
        this.element = element;
    }

    public static MobileGetIsOnTheScreenOperationType of(@NotNull MobileChildElementBase element) {
        return new MobileGetIsOnTheScreenOperationType(element);
    }

    @Override
    public @NotNull InvocationName getInvocationName() {
        return InvocationName.getterInvocation(IS_ON_THE_SCREEN_METHOD, element);
    }

    @Override
    public @NotNull EndpointHandler<Boolean> getEndpointHandler() {
        Class<? extends EndpointHandler<Boolean>> endpointHandlerClass = element.getEndpointHandler(IS_ON_THE_SCREEN_METHOD, Boolean.class);
        Constructor<? extends EndpointHandler<Boolean>> constructor = getDeclaredConstructor(endpointHandlerClass);
        return newInstance(constructor, element);
    }

}
