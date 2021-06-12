package io.perfeccionista.framework.pagefactory.operation.type;

import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.pagefactory.elements.methods.WebHoverToAvailable;
import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;

import static io.perfeccionista.framework.invocation.runner.InvocationName.actionInvocation;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.HOVER_TO_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.HOVER;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.getDeclaredConstructor;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.newInstance;

public class WebHoverToOperationType implements WebElementOperationType<Void> {

    private final WebHoverToAvailable element;
    private final boolean withOutOfBounds;

    private WebHoverToOperationType(WebHoverToAvailable element, boolean withOutOfBounds) {
        this.element = element;
        this.withOutOfBounds = withOutOfBounds;
    }

    public static WebHoverToOperationType of(@NotNull WebHoverToAvailable element, boolean withOutOfBounds) {
        return new WebHoverToOperationType(element, withOutOfBounds);
    }

    @Override
    public @NotNull InvocationName getInvocationName() {
        return actionInvocation(HOVER_TO_METHOD, element, HOVER, withOutOfBounds);
    }

    @Override
    public @NotNull EndpointHandler<Void> getEndpointHandler() {
        Class<? extends EndpointHandler<Void>> endpointHandlerClass = element.getEndpointHandler(HOVER_TO_METHOD, Void.class);
        Constructor<? extends EndpointHandler<Void>> constructor = getDeclaredConstructor(endpointHandlerClass);
        return newInstance(constructor, element, withOutOfBounds);
    }

}
