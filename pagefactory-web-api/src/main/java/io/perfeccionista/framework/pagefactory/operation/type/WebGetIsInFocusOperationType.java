package io.perfeccionista.framework.pagefactory.operation.type;

import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.pagefactory.elements.methods.WebIsInFocusAvailable;
import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;

import static io.perfeccionista.framework.invocation.runner.InvocationInfo.getterInvocation;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.IS_IN_FOCUS_METHOD;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.getDeclaredConstructor;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.newInstance;

public class WebGetIsInFocusOperationType implements WebElementOperationType<Boolean> {

    private final WebIsInFocusAvailable element;

    private final InvocationInfo invocationInfo;

    private WebGetIsInFocusOperationType(WebIsInFocusAvailable element) {
        this.element = element;
        var elementName = element.getElementIdentifier().getLastUsedName();
        this.invocationInfo = getterInvocation(IS_IN_FOCUS_METHOD, elementName);
    }

    public static WebGetIsInFocusOperationType of(@NotNull WebIsInFocusAvailable element) {
        return new WebGetIsInFocusOperationType(element);
    }

    @Override
    public @NotNull InvocationInfo getInvocationName() {
        return this.invocationInfo;
    }

    @Override
    public @NotNull EndpointHandler<Boolean> getEndpointHandler() {
        Class<? extends EndpointHandler<Boolean>> endpointHandlerClass = element.getEndpointHandler(IS_IN_FOCUS_METHOD, Boolean.class);
        Constructor<? extends EndpointHandler<Boolean>> constructor = getDeclaredConstructor(endpointHandlerClass);
        return newInstance(constructor);
    }

}
