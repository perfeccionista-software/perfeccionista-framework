package io.perfeccionista.framework.pagefactory.operation.type;

import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElementBase;
import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;

import static io.perfeccionista.framework.invocation.runner.InvocationInfo.getterInvocation;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.GET_STRING_ATTRIBUTE_VALUE_METHOD;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.getDeclaredConstructor;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.newInstance;

public class WebGetStringAttributeValueOperationType implements WebElementOperationType<String> {

    private final WebChildElementBase element;
    private final String attributeName;

    private final InvocationInfo invocationInfo;

    private WebGetStringAttributeValueOperationType(WebChildElementBase element, String attributeName) {
        this.element = element;
        this.attributeName = attributeName;
        var elementName = element.getElementIdentifier().getLastUsedName();
        this.invocationInfo = getterInvocation(GET_STRING_ATTRIBUTE_VALUE_METHOD, elementName, attributeName);
    }

    public static WebGetStringAttributeValueOperationType of(@NotNull WebChildElementBase element, @NotNull String attributeName) {
        return new WebGetStringAttributeValueOperationType(element, attributeName);
    }

    @Override
    public @NotNull InvocationInfo getInvocationName() {
        return this.invocationInfo;
    }

    @Override
    public @NotNull EndpointHandler<String> getEndpointHandler() {
        Class<? extends EndpointHandler<String>> endpointHandlerClass = element.getEndpointHandler(GET_STRING_ATTRIBUTE_VALUE_METHOD, String.class);
        Constructor<? extends EndpointHandler<String>> constructor = getDeclaredConstructor(endpointHandlerClass);
        return newInstance(constructor, attributeName);
    }

}
