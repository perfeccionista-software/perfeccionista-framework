package io.perfeccionista.framework.pagefactory.operation.type;

import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElementBase;
import io.perfeccionista.framework.pagefactory.elements.properties.base.MobileElementPropertyHolder;
import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;

import static io.perfeccionista.framework.invocation.runner.InvocationInfo.getterInvocation;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.GET_ATTRIBUTE_VALUE_METHOD;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.getDeclaredConstructor;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.newInstance;

public class MobileGetPropertyValueOperationType implements MobileElementOperationType<String> {

    private final MobileChildElementBase element;
    private final MobileElementPropertyHolder propertyHolder;

    private final InvocationInfo invocationInfo;

    private MobileGetPropertyValueOperationType(MobileChildElementBase element, MobileElementPropertyHolder propertyHolder) {
        this.element = element;
        this.propertyHolder = propertyHolder;
        String elementName = element.getElementIdentifier().getLastUsedName();
        this.invocationInfo = getterInvocation(GET_ATTRIBUTE_VALUE_METHOD, elementName, propertyHolder.getName());
    }

    public static MobileGetPropertyValueOperationType of(@NotNull MobileChildElementBase element, @NotNull MobileElementPropertyHolder propertyHolder) {
        return new MobileGetPropertyValueOperationType(element, propertyHolder);
    }

    @Override
    public @NotNull InvocationInfo getInvocationName() {
        return this.invocationInfo;
    }

    @Override
    public @NotNull EndpointHandler<String> getEndpointHandler() {
        Class<? extends EndpointHandler<String>> endpointHandlerClass = element.getEndpointHandler(GET_ATTRIBUTE_VALUE_METHOD, String.class);
        Constructor<? extends EndpointHandler<String>> constructor = getDeclaredConstructor(endpointHandlerClass);
        return newInstance(constructor, element, propertyHolder);
    }

}

