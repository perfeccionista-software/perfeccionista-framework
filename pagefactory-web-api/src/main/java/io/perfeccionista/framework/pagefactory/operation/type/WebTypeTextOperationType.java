package io.perfeccionista.framework.pagefactory.operation.type;

import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.pagefactory.elements.methods.WebInputTextAvailable;
import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;

import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.TYPE_TEXT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.INPUT;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.getDeclaredConstructor;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.newInstance;

public class WebTypeTextOperationType implements WebElementOperationType<Void> {

    private final WebInputTextAvailable element;
    private final String text;

    private WebTypeTextOperationType(WebInputTextAvailable element, String text) {
        this.element = element;
        this.text = text;
    }

    public static WebTypeTextOperationType of(@NotNull WebInputTextAvailable element, @NotNull String text) {
        return new WebTypeTextOperationType(element, text);
    }

    @Override
    public @NotNull InvocationInfo getInvocationName() {
        return InvocationInfo.actionInvocation(TYPE_TEXT_METHOD, element, INPUT, text);
    }

    @Override
    public @NotNull EndpointHandler<Void> getEndpointHandler() {
        Class<? extends EndpointHandler<Void>> endpointHandlerClass = element.getEndpointHandler(TYPE_TEXT_METHOD, Void.class);
        Constructor<? extends EndpointHandler<Void>> constructor = getDeclaredConstructor(endpointHandlerClass);
        return newInstance(constructor, text);
    }

}
