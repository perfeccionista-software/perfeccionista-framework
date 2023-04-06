package io.perfeccionista.framework.pagefactory.operation.type;

import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.pagefactory.elements.methods.WebClickAvailable;
import io.perfeccionista.framework.pagefactory.elements.options.ContextClickOptions;
import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;

import static io.perfeccionista.framework.invocation.runner.InvocationInfo.actionInvocation;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.CONTEXT_CLICK_METHOD;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.getDeclaredConstructor;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.newInstance;

public class WebContextClickOperationType implements WebElementOperationType<Void> {

    private final WebClickAvailable element;
    private final ContextClickOptions options;

    private final InvocationInfo invocationInfo;

    private WebContextClickOperationType(WebClickAvailable element, ContextClickOptions options) {
        this.element = element;
        this.options = options;
        String elementName = element.getElementIdentifier().getLastUsedName();
        this.invocationInfo = actionInvocation(CONTEXT_CLICK_METHOD, elementName, options.toString());
    }

    public static WebContextClickOperationType of(@NotNull WebClickAvailable element, @NotNull ContextClickOptions options) {
        return new WebContextClickOperationType(element, options);
    }

    @Override
    public @NotNull InvocationInfo getInvocationName() {
        return this.invocationInfo;
    }

    @Override
    public @NotNull EndpointHandler<Void> getEndpointHandler() {
        Class<? extends EndpointHandler<Void>> endpointHandlerClass = element.getEndpointHandler(CONTEXT_CLICK_METHOD, Void.class);
        Constructor<? extends EndpointHandler<Void>> constructor = getDeclaredConstructor(endpointHandlerClass);
        return newInstance(constructor, options);
    }

}
