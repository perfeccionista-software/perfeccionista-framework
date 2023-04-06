package io.perfeccionista.framework.pagefactory.operation.type;

import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.pagefactory.elements.methods.WebClickAvailable;
import io.perfeccionista.framework.pagefactory.elements.options.DoubleClickOptions;
import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;

import static io.perfeccionista.framework.invocation.runner.InvocationInfo.actionInvocation;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.DOUBLE_CLICK_METHOD;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.getDeclaredConstructor;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.newInstance;

public class WebDoubleClickOperationType implements WebElementOperationType<Void> {

    private final WebClickAvailable element;
    private final DoubleClickOptions options;

    private final InvocationInfo invocationInfo;

    private WebDoubleClickOperationType(WebClickAvailable element, DoubleClickOptions options) {
        this.element = element;
        this.options = options;
        String elementName = element.getElementIdentifier().getLastUsedName();
        this.invocationInfo = actionInvocation(DOUBLE_CLICK_METHOD, elementName, options.toString());
    }

    public static WebDoubleClickOperationType of(@NotNull WebClickAvailable element, @NotNull DoubleClickOptions options) {
        return new WebDoubleClickOperationType(element, options);
    }

    @Override
    public @NotNull InvocationInfo getInvocationName() {
        return this.invocationInfo;
    }

    @Override
    public @NotNull EndpointHandler<Void> getEndpointHandler() {
        Class<? extends EndpointHandler<Void>> endpointHandlerClass = element.getEndpointHandler(DOUBLE_CLICK_METHOD, Void.class);
        Constructor<? extends EndpointHandler<Void>> constructor = getDeclaredConstructor(endpointHandlerClass);
        return newInstance(constructor, options);
    }

}
