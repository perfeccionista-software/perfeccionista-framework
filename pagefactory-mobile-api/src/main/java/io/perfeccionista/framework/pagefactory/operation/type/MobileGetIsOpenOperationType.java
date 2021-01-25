package io.perfeccionista.framework.pagefactory.operation.type;

import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElementBase;
import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;

import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.IS_OPEN_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.UL;
import static org.junit.platform.commons.util.ReflectionUtils.getDeclaredConstructor;
import static org.junit.platform.commons.util.ReflectionUtils.newInstance;

public class MobileGetIsOpenOperationType implements MobileElementOperationType<Boolean> {

    private final MobileChildElementBase element;

    private MobileGetIsOpenOperationType(MobileChildElementBase element) {
        this.element = element;
    }

    public static MobileGetIsOpenOperationType of(@NotNull MobileChildElementBase element) {
        return new MobileGetIsOpenOperationType(element);
    }

    @Override
    public @NotNull InvocationName getInvocationName() {
        return InvocationName.getterInvocation(IS_OPEN_METHOD, element, UL);
    }

    @Override
    public @NotNull EndpointHandler<Boolean> getEndpointHandler() {
        Class<? extends EndpointHandler<Boolean>> endpointHandlerClass = element.getEndpointHandler(IS_OPEN_METHOD, Boolean.class);
        Constructor<? extends EndpointHandler<Boolean>> constructor = getDeclaredConstructor(endpointHandlerClass);
        return newInstance(constructor, element);
    }

}
