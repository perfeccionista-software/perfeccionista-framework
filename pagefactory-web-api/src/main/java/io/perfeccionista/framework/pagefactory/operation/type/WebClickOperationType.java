package io.perfeccionista.framework.pagefactory.operation.type;

import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.pagefactory.elements.methods.WebClickAvailable;
import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;

import static io.perfeccionista.framework.invocation.runner.InvocationInfo.actionInvocation;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.CLICK_METHOD;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.getDeclaredConstructor;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.newInstance;

public class WebClickOperationType implements WebElementOperationType<Void> {

    private final WebClickAvailable element;

    private final InvocationInfo invocationInfo;

    private WebClickOperationType(WebClickAvailable element) {
        this.element = element;
        String elementName = element.getElementIdentifier().getLastUsedName();
        this.invocationInfo = actionInvocation(CLICK_METHOD, elementName);
    }

    public static WebClickOperationType of(@NotNull WebClickAvailable element) {
        return new WebClickOperationType(element);
    }

    @Override
    public @NotNull InvocationInfo getInvocationName() {
        return this.invocationInfo;
    }

    @Override
    public @NotNull EndpointHandler<Void> getEndpointHandler() {
        Class<? extends EndpointHandler<Void>> endpointHandlerClass = element.getEndpointHandler(CLICK_METHOD, Void.class);
        Constructor<? extends EndpointHandler<Void>> constructor = getDeclaredConstructor(endpointHandlerClass);
        return newInstance(constructor);
    }

}
