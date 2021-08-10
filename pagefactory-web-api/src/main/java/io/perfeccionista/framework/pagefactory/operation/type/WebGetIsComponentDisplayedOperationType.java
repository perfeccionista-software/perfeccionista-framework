package io.perfeccionista.framework.pagefactory.operation.type;

import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.pagefactory.elements.methods.WebComponentAvailable;
import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;

import static io.perfeccionista.framework.invocation.runner.InvocationInfo.getterInvocation;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.IS_COMPONENT_DISPLAYED_METHOD;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.getDeclaredConstructor;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.newInstance;

public class WebGetIsComponentDisplayedOperationType implements WebElementOperationType<Boolean> {

    private final WebComponentAvailable element;

    private final InvocationInfo invocationInfo;

    private WebGetIsComponentDisplayedOperationType(WebComponentAvailable element, String componentName) {
        this.element = element;
        String elementName = element.getElementIdentifier().getLastUsedName();
        this.invocationInfo = getterInvocation(IS_COMPONENT_DISPLAYED_METHOD, elementName, componentName);
    }

    public static WebGetIsComponentDisplayedOperationType of(@NotNull WebComponentAvailable element, @NotNull String componentName) {
        return new WebGetIsComponentDisplayedOperationType(element, componentName);
    }

    @Override
    public @NotNull InvocationInfo getInvocationName() {
        return this.invocationInfo;
    }

    @Override
    public @NotNull EndpointHandler<Boolean> getEndpointHandler() {
        Class<? extends EndpointHandler<Boolean>> endpointHandlerClass = element.getEndpointHandler(IS_COMPONENT_DISPLAYED_METHOD, Boolean.class);
        Constructor<? extends EndpointHandler<Boolean>> constructor = getDeclaredConstructor(endpointHandlerClass);
        return newInstance(constructor);
    }

}
