package io.perfeccionista.framework.pagefactory.operation.type;

import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.pagefactory.elements.methods.WebComponentAvailable;
import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;

import static io.perfeccionista.framework.invocation.runner.InvocationName.getterInvocation;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.IS_COMPONENT_PRESENT_METHOD;
import static org.junit.platform.commons.util.ReflectionUtils.getDeclaredConstructor;
import static org.junit.platform.commons.util.ReflectionUtils.newInstance;

public class WebGetIsComponentPresentOperationType implements WebElementOperationType<Boolean> {

    private final WebComponentAvailable element;
    private final String componentName;

    private WebGetIsComponentPresentOperationType(WebComponentAvailable element, String componentName) {
        this.element = element;
        this.componentName = componentName;
    }

    public static WebGetIsComponentPresentOperationType of(@NotNull WebComponentAvailable element, @NotNull String componentName) {
        return new WebGetIsComponentPresentOperationType(element, componentName);
    }

    @Override
    public @NotNull InvocationName getInvocationName() {
        return getterInvocation(IS_COMPONENT_PRESENT_METHOD, element, componentName);
    }

    @Override
    public @NotNull EndpointHandler<Boolean> getEndpointHandler() {
        Class<? extends EndpointHandler<Boolean>> endpointHandlerClass = element.getEndpointHandler(IS_COMPONENT_PRESENT_METHOD, Boolean.class);
        Constructor<? extends EndpointHandler<Boolean>> constructor = getDeclaredConstructor(endpointHandlerClass);
        return newInstance(constructor);
    }

}
