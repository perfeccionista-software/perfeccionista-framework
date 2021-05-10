package io.perfeccionista.framework.pagefactory.operation.type;

import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElementBase;
import io.perfeccionista.framework.pagefactory.elements.states.base.MobileElementStateHolder;
import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;

import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.HAS_STATE_METHOD;
import static org.junit.platform.commons.util.ReflectionUtils.getDeclaredConstructor;
import static org.junit.platform.commons.util.ReflectionUtils.newInstance;

public class MobileHasStateOperationType implements MobileElementOperationType<Boolean> {

    private final MobileChildElementBase element;
    private final MobileElementStateHolder stateHolder;

    private MobileHasStateOperationType(MobileChildElementBase element, MobileElementStateHolder stateHolder) {
        this.element = element;
        this.stateHolder = stateHolder;
    }

    public static MobileHasStateOperationType of(@NotNull MobileChildElementBase element, @NotNull MobileElementStateHolder stateHolder) {
        return new MobileHasStateOperationType(element, stateHolder);
    }

    @Override
    public @NotNull InvocationName getInvocationName() {
        return InvocationName.getterInvocation(HAS_STATE_METHOD, element, stateHolder);
    }

    @Override
    public @NotNull EndpointHandler<Boolean> getEndpointHandler() {
        Class<? extends EndpointHandler<Boolean>> endpointHandlerClass = element.getEndpointHandler(HAS_STATE_METHOD, Boolean.class);
        Constructor<? extends EndpointHandler<Boolean>> constructor = getDeclaredConstructor(endpointHandlerClass);
        return newInstance(constructor, element, stateHolder);
    }

}
