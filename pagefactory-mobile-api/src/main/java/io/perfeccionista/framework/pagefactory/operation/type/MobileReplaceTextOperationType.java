package io.perfeccionista.framework.pagefactory.operation.type;

import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElementBase;
import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;

import static io.perfeccionista.framework.invocation.runner.InvocationInfo.actionInvocation;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.REPLACE_TEXT_METHOD;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.getDeclaredConstructor;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.newInstance;

public class MobileReplaceTextOperationType implements MobileElementOperationType<Void> {

    private final MobileChildElementBase element;
    private final String valueToInput;

    private final InvocationInfo invocationInfo;

    private MobileReplaceTextOperationType(MobileChildElementBase element, String valueToInput) {
        this.element = element;
        this.valueToInput = valueToInput;
        String elementName = element.getElementIdentifier().getLastUsedName();
        this.invocationInfo = actionInvocation(REPLACE_TEXT_METHOD, elementName, valueToInput);
    }

    public static MobileReplaceTextOperationType of(@NotNull MobileChildElementBase element, @NotNull String valueToInput) {
        return new MobileReplaceTextOperationType(element, valueToInput);
    }

    @Override
    public @NotNull InvocationInfo getInvocationName() {
        return this.invocationInfo;
    }

    @Override
    public @NotNull EndpointHandler<Void> getEndpointHandler() {
        Class<? extends EndpointHandler<Void>> endpointHandlerClass = element.getEndpointHandler(REPLACE_TEXT_METHOD, Void.class);
        Constructor<? extends EndpointHandler<Void>> constructor = getDeclaredConstructor(endpointHandlerClass);
        return newInstance(constructor, element, valueToInput);
    }

}


