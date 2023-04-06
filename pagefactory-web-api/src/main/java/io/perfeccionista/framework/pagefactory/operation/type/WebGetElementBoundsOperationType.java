package io.perfeccionista.framework.pagefactory.operation.type;

import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.pagefactory.elements.ElementBounds;
import io.perfeccionista.framework.pagefactory.elements.methods.WebGetElementBoundsAvailable;
import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;

import static io.perfeccionista.framework.invocation.runner.InvocationInfo.getterInvocation;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.GET_ELEMENT_BOUNDS_METHOD;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.getDeclaredConstructor;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.newInstance;

public class WebGetElementBoundsOperationType implements WebElementOperationType<ElementBounds> {

    private static final String ACTION_TYPE = GET_ELEMENT_BOUNDS_METHOD;

    private final WebGetElementBoundsAvailable element;
    private final String componentName;

    private final InvocationInfo invocationInfo;

    private WebGetElementBoundsOperationType(WebGetElementBoundsAvailable element, String componentName) {
        this.element = element;
        this.componentName = componentName;
        String elementName = element.getElementIdentifier().getLastUsedName();
        this.invocationInfo = getterInvocation(ACTION_TYPE, elementName, componentName);
    }

    public static WebGetElementBoundsOperationType of(@NotNull WebGetElementBoundsAvailable element, @NotNull String componentName) {
        return new WebGetElementBoundsOperationType(element, componentName);
    }

    @Override
    public @NotNull InvocationInfo getInvocationName() {
        return this.invocationInfo;
    }

    @Override
    public @NotNull EndpointHandler<ElementBounds> getEndpointHandler() {
        Class<? extends EndpointHandler<ElementBounds>> endpointHandlerClass = element.getEndpointHandler(ACTION_TYPE, ElementBounds.class);
        Constructor<? extends EndpointHandler<ElementBounds>> constructor = getDeclaredConstructor(endpointHandlerClass);
        return newInstance(constructor);
    }

}
