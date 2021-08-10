package io.perfeccionista.framework.pagefactory.operation.type;

import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElementBase;
import io.perfeccionista.framework.pagefactory.elements.states.base.MobileElementStateHolder;
import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;

import static io.perfeccionista.framework.invocation.runner.InvocationInfo.getterInvocation;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.HAS_STATE_METHOD;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.getDeclaredConstructor;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.newInstance;

public class MobileHasStateOperationType implements MobileElementOperationType<Boolean> {

    private final MobileChildElementBase element;
    private final MobileElementStateHolder stateHolder;

    private final InvocationInfo invocationInfo;

    private MobileHasStateOperationType(MobileChildElementBase element, MobileElementStateHolder stateHolder) {
        this.element = element;
        this.stateHolder = stateHolder;
        String elementName = element.getElementIdentifier().getLastUsedName();
        this.invocationInfo = getterInvocation(HAS_STATE_METHOD, elementName, stateHolder.getName());
    }

    public static MobileHasStateOperationType of(@NotNull MobileChildElementBase element, @NotNull MobileElementStateHolder stateHolder) {
        return new MobileHasStateOperationType(element, stateHolder);
    }

    @Override
    public @NotNull InvocationInfo getInvocationName() {
        return this.invocationInfo;
    }

    @Override
    public @NotNull EndpointHandler<Boolean> getEndpointHandler() {
        Class<? extends EndpointHandler<Boolean>> endpointHandlerClass = element.getEndpointHandler(HAS_STATE_METHOD, Boolean.class);
        Constructor<? extends EndpointHandler<Boolean>> constructor = getDeclaredConstructor(endpointHandlerClass);
        return newInstance(constructor, element, stateHolder);
    }

}
