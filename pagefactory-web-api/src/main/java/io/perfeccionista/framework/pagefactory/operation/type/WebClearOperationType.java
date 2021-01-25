package io.perfeccionista.framework.pagefactory.operation.type;

import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.pagefactory.elements.methods.WebInputTextAvailable;
import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;

import static io.perfeccionista.framework.invocation.runner.InvocationName.actionInvocation;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.CLEAR_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.CLEAR;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.INPUT;
import static org.junit.platform.commons.util.ReflectionUtils.getDeclaredConstructor;
import static org.junit.platform.commons.util.ReflectionUtils.newInstance;

public class WebClearOperationType implements WebElementOperationType<Void> {

    private final WebInputTextAvailable element;

    private WebClearOperationType(WebInputTextAvailable element) {
        this.element = element;
    }

    public static WebClearOperationType of(@NotNull WebInputTextAvailable element) {
        return new WebClearOperationType(element);
    }

    @Override
    public @NotNull InvocationName getInvocationName() {
        return actionInvocation(CLEAR_METHOD, element, INPUT);
    }

    @Override
    public @NotNull EndpointHandler<Void> getEndpointHandler() {
        Class<? extends EndpointHandler<Void>> endpointHandlerClass = element.getEndpointHandler(CLEAR_METHOD, Void.class);
        Constructor<? extends EndpointHandler<Void>> constructor = getDeclaredConstructor(endpointHandlerClass);
        return newInstance(constructor);
    }

}
