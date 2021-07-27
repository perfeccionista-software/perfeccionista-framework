package io.perfeccionista.framework.pagefactory.operation.type;

import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElementBase;
import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;

import static io.perfeccionista.framework.invocation.runner.InvocationInfo.getterInvocation;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.IS_ON_THE_SCREEN_METHOD;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.getDeclaredConstructor;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.newInstance;

public class MobileGetIsOnTheScreenOperationType implements MobileElementOperationType<Boolean> {

    private final MobileChildElementBase element;

    private final InvocationInfo invocationInfo;

    private MobileGetIsOnTheScreenOperationType(MobileChildElementBase element) {
        this.element = element;
        var elementName = element.getElementIdentifier().getLastUsedName();
        this.invocationInfo = getterInvocation(IS_ON_THE_SCREEN_METHOD, elementName);
    }

    public static MobileGetIsOnTheScreenOperationType of(@NotNull MobileChildElementBase element) {
        return new MobileGetIsOnTheScreenOperationType(element);
    }

    @Override
    public @NotNull InvocationInfo getInvocationName() {
        return this.invocationInfo;
    }

    @Override
    public @NotNull EndpointHandler<Boolean> getEndpointHandler() {
        Class<? extends EndpointHandler<Boolean>> endpointHandlerClass = element.getEndpointHandler(IS_ON_THE_SCREEN_METHOD, Boolean.class);
        Constructor<? extends EndpointHandler<Boolean>> constructor = getDeclaredConstructor(endpointHandlerClass);
        return newInstance(constructor, element);
    }

}
