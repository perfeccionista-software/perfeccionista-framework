package io.perfeccionista.framework.pagefactory.operation.type;

import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElementBase;
import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;

import static io.perfeccionista.framework.invocation.runner.InvocationInfo.actionInvocation;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.TYPE_TEXT_METHOD;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.getDeclaredConstructor;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.newInstance;

public class MobileTypeTextOperationType implements MobileElementOperationType<Void> {

    private final MobileChildElementBase element;
    private final String text;

    private final InvocationInfo invocationInfo;

    private MobileTypeTextOperationType(MobileChildElementBase element, String text) {
        this.element = element;
        this.text = text;
        String elementName = element.getElementIdentifier().getLastUsedName();
        this.invocationInfo = actionInvocation(TYPE_TEXT_METHOD, elementName, text);
    }

    public static MobileTypeTextOperationType of(@NotNull MobileChildElementBase element, @NotNull String valueToInput) {
        return new MobileTypeTextOperationType(element, valueToInput);
    }

    @Override
    public @NotNull InvocationInfo getInvocationName() {
        return this.invocationInfo;
    }

    @Override
    public @NotNull EndpointHandler<Void> getEndpointHandler() {
        Class<? extends EndpointHandler<Void>> endpointHandlerClass = element.getEndpointHandler(TYPE_TEXT_METHOD, Void.class);
        Constructor<? extends EndpointHandler<Void>> constructor = getDeclaredConstructor(endpointHandlerClass);
        return newInstance(constructor, element, text);
    }

}

