package io.perfeccionista.framework.pagefactory.operation.type;

import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.pagefactory.elements.methods.WebScrollToAvailable;
import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;

import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SCROLL_TO_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.ROOT;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.getDeclaredConstructor;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.newInstance;

public class WebScrollToOperationType implements WebElementOperationType<Void> {

    private final WebScrollToAvailable element;

    private WebScrollToOperationType(WebScrollToAvailable element) {
        this.element = element;
    }

    public static WebScrollToOperationType of(@NotNull WebScrollToAvailable element) {
        return new WebScrollToOperationType(element);
    }

    @Override
    public @NotNull InvocationInfo getInvocationName() {
        return InvocationInfo.actionInvocation(SCROLL_TO_METHOD, element, ROOT);
    }

    @Override
    public @NotNull EndpointHandler<Void> getEndpointHandler() {
        Class<? extends EndpointHandler<Void>> endpointHandlerClass = element.getEndpointHandler(SCROLL_TO_METHOD, Void.class);
        Constructor<? extends EndpointHandler<Void>> constructor = getDeclaredConstructor(endpointHandlerClass);
        return newInstance(constructor);
    }

}
