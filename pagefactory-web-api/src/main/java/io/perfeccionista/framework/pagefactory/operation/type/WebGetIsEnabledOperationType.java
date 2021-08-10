package io.perfeccionista.framework.pagefactory.operation.type;

import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.pagefactory.elements.methods.WebIsEnabledAvailable;
import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;

import static io.perfeccionista.framework.invocation.runner.InvocationInfo.getterInvocation;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.IS_ENABLED_METHOD;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.getDeclaredConstructor;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.newInstance;

public class WebGetIsEnabledOperationType implements WebElementOperationType<Boolean> {

    private final WebIsEnabledAvailable element;

    private final InvocationInfo invocationInfo;

    private WebGetIsEnabledOperationType(WebIsEnabledAvailable element) {
        this.element = element;
        String elementName = element.getElementIdentifier().getLastUsedName();
        this.invocationInfo = getterInvocation(IS_ENABLED_METHOD, elementName);
    }

    public static WebGetIsEnabledOperationType of(@NotNull WebIsEnabledAvailable element) {
        return new WebGetIsEnabledOperationType(element);
    }

    @Override
    public @NotNull InvocationInfo getInvocationName() {
        return this.invocationInfo;
    }

    @Override
    public @NotNull EndpointHandler<Boolean> getEndpointHandler() {
        Class<? extends EndpointHandler<Boolean>> endpointHandlerClass = element.getEndpointHandler(IS_ENABLED_METHOD, Boolean.class);
        Constructor<? extends EndpointHandler<Boolean>> constructor = getDeclaredConstructor(endpointHandlerClass);
        return newInstance(constructor);
    }

}
