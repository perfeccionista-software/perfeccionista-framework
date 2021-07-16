package io.perfeccionista.framework.pagefactory.operation.type;

import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.pagefactory.elements.methods.WebHoverToAvailable;
import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;

import static io.perfeccionista.framework.invocation.runner.InvocationInfo.actionInvocation;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.HOVER_TO_METHOD;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.getDeclaredConstructor;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.newInstance;

public class WebHoverToOperationType implements WebElementOperationType<Void> {

    private final WebHoverToAvailable element;
    private final boolean withOutOfBounds;

    private final InvocationInfo invocationInfo;

    private WebHoverToOperationType(WebHoverToAvailable element, boolean withOutOfBounds) {
        this.element = element;
        this.withOutOfBounds = withOutOfBounds;
        var elementName = element.getElementIdentifier().getLastUsedName();
        this.invocationInfo = actionInvocation(HOVER_TO_METHOD, elementName);
    }

    public static WebHoverToOperationType of(@NotNull WebHoverToAvailable element, boolean withOutOfBounds) {
        return new WebHoverToOperationType(element, withOutOfBounds);
    }

    @Override
    public @NotNull InvocationInfo getInvocationName() {
        return this.invocationInfo;
    }

    @Override
    public @NotNull EndpointHandler<Void> getEndpointHandler() {
        Class<? extends EndpointHandler<Void>> endpointHandlerClass = element.getEndpointHandler(HOVER_TO_METHOD, Void.class);
        Constructor<? extends EndpointHandler<Void>> constructor = getDeclaredConstructor(endpointHandlerClass);
        return newInstance(constructor, element, withOutOfBounds);
    }

}
