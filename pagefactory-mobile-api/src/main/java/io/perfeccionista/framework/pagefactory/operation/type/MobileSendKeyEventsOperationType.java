package io.perfeccionista.framework.pagefactory.operation.type;

import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElementBase;
import io.perfeccionista.framework.pagefactory.emulator.keys.KeyEventsChain;
import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;

import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SEND_KEY_EVENTS_METHOD;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.getDeclaredConstructor;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.newInstance;

public class MobileSendKeyEventsOperationType implements MobileElementOperationType<Void> {

    private final MobileChildElementBase element;
    private final KeyEventsChain keyEvents;

    private MobileSendKeyEventsOperationType(MobileChildElementBase element, KeyEventsChain keyEvents) {
        this.element = element;
        this.keyEvents = keyEvents;
    }

    public static MobileSendKeyEventsOperationType of(@NotNull MobileChildElementBase element, @NotNull KeyEventsChain keyEvents) {
        return new MobileSendKeyEventsOperationType(element, keyEvents);
    }

    @Override
    public @NotNull InvocationName getInvocationName() {
        return InvocationName.actionInvocation(SEND_KEY_EVENTS_METHOD, element, keyEvents);
    }

    @Override
    public @NotNull EndpointHandler<Void> getEndpointHandler() {
        Class<? extends EndpointHandler<Void>> endpointHandlerClass = element.getEndpointHandler(SEND_KEY_EVENTS_METHOD, Void.class);
        Constructor<? extends EndpointHandler<Void>> constructor = getDeclaredConstructor(endpointHandlerClass);
        return newInstance(constructor, element, keyEvents);
    }

}

