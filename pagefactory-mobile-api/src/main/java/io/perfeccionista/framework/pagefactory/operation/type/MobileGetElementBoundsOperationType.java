package io.perfeccionista.framework.pagefactory.operation.type;

import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.pagefactory.elements.ElementBounds;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElementBase;
import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;

import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.GET_ELEMENT_BOUNDS_METHOD;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.getDeclaredConstructor;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.newInstance;

public class MobileGetElementBoundsOperationType implements MobileElementOperationType<ElementBounds> {

    private final MobileChildElementBase element;
    private final String componentName;

    private MobileGetElementBoundsOperationType(@NotNull MobileChildElementBase element, @NotNull String componentName) {
        this.element = element;
        this.componentName = componentName;
    }

    public static MobileGetElementBoundsOperationType of(@NotNull MobileChildElementBase element, @NotNull String componentName) {
        return new MobileGetElementBoundsOperationType(element, componentName);
    }

    @Override
    public @NotNull InvocationName getInvocationName() {
        return InvocationName.getterInvocation(GET_ELEMENT_BOUNDS_METHOD, element, componentName);
    }

    @Override
    public @NotNull EndpointHandler<ElementBounds> getEndpointHandler() {
        Class<? extends EndpointHandler<ElementBounds>> endpointHandlerClass = element.getEndpointHandler(GET_ELEMENT_BOUNDS_METHOD, ElementBounds.class);
        Constructor<? extends EndpointHandler<ElementBounds>> constructor = getDeclaredConstructor(endpointHandlerClass);
        return newInstance(constructor, element, componentName);
    }

}
