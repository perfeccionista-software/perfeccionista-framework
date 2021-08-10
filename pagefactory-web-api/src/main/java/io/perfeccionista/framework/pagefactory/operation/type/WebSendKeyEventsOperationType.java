package io.perfeccionista.framework.pagefactory.operation.type;

import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.pagefactory.elements.methods.WebHoverToAvailable;
import io.perfeccionista.framework.pagefactory.emulator.keys.KeyEventsChain;
import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;

import static io.perfeccionista.framework.invocation.runner.InvocationInfo.actionInvocation;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SEND_KEY_EVENTS_METHOD;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.getDeclaredConstructor;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.newInstance;

public class WebSendKeyEventsOperationType implements WebElementOperationType<Void> {

    private final WebHoverToAvailable element;
    private final KeyEventsChain keyEvents;

    private final InvocationInfo invocationInfo;

    private WebSendKeyEventsOperationType(WebHoverToAvailable element, KeyEventsChain keyEvents) {
        this.element = element;
        this.keyEvents = keyEvents;
        String elementName = element.getElementIdentifier().getLastUsedName();
        this.invocationInfo = actionInvocation(SEND_KEY_EVENTS_METHOD, elementName, keyEvents.toString());
    }

    public static WebSendKeyEventsOperationType of(@NotNull WebHoverToAvailable element, @NotNull KeyEventsChain keyEvents) {
        return new WebSendKeyEventsOperationType(element, keyEvents);
    }

    @Override
    public @NotNull InvocationInfo getInvocationName() {
        return this.invocationInfo;
    }

    @Override
    public @NotNull EndpointHandler<Void> getEndpointHandler() {
        Class<? extends EndpointHandler<Void>> endpointHandlerClass = element.getEndpointHandler(SEND_KEY_EVENTS_METHOD, Void.class);
        Constructor<? extends EndpointHandler<Void>> constructor = getDeclaredConstructor(endpointHandlerClass);
        return newInstance(constructor, keyEvents);
    }

}
