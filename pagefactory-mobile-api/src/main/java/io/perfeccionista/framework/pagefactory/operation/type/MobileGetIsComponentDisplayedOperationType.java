package io.perfeccionista.framework.pagefactory.operation.type;

import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElementBase;
import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;

import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.IS_COMPONENT_DISPLAYED_METHOD;
import static org.junit.platform.commons.util.ReflectionUtils.getDeclaredConstructor;
import static org.junit.platform.commons.util.ReflectionUtils.newInstance;

public class MobileGetIsComponentDisplayedOperationType implements MobileElementOperationType<Boolean> {

    private final MobileChildElementBase element;
    private final String componentName;

    private MobileGetIsComponentDisplayedOperationType(@NotNull MobileChildElementBase element, @NotNull String componentName) {
        this.element = element;
        this.componentName = componentName;
    }

    public static MobileGetIsComponentDisplayedOperationType of(@NotNull MobileChildElementBase element, @NotNull String componentName) {
        return new MobileGetIsComponentDisplayedOperationType(element, componentName);
    }

    @Override
    public @NotNull InvocationName getInvocationName() {
        return InvocationName.getterInvocation(IS_COMPONENT_DISPLAYED_METHOD, element, componentName);
    }

    @Override
    public @NotNull EndpointHandler<Boolean> getEndpointHandler() {
        Class<? extends EndpointHandler<Boolean>> endpointHandlerClass = element.getEndpointHandler(IS_COMPONENT_DISPLAYED_METHOD, Boolean.class);
        Constructor<? extends EndpointHandler<Boolean>> constructor = getDeclaredConstructor(endpointHandlerClass);
        return newInstance(constructor, element);
    }

}
