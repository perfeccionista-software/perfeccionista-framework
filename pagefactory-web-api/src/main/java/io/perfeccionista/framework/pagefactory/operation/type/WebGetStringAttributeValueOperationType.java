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

    private static final String ACTION_NAME = GET_STRING_ATTRIBUTE_VALUE_METHOD;

    private final WebChildElementBase element;
    private final String attributeName;

    private final InvocationInfo invocationInfo;

    private WebGetStringAttributeValueOperationType(WebChildElementBase element, String componentName, String attributeName) {
        this.element = element;
        this.attributeName = attributeName;
        String elementName = element.getElementIdentifier().getLastUsedName();
        this.invocationInfo = getterInvocation(ACTION_NAME, elementName, componentName, attributeName);
    }

    public static WebGetStringAttributeValueOperationType of(@NotNull WebChildElementBase element,
                                                             @NotNull String componentName,
                                                             @NotNull String attributeName) {
        return new WebGetStringAttributeValueOperationType(element, componentName, attributeName);
    }

    @Override
    public @NotNull InvocationInfo getInvocationName() {
        return this.invocationInfo;
    }

    @Override
    public @NotNull EndpointHandler<String> getEndpointHandler() {
        Class<? extends EndpointHandler<String>> endpointHandlerClass = element.getEndpointHandler(ACTION_NAME, String.class);
        Constructor<? extends EndpointHandler<String>> constructor = getDeclaredConstructor(endpointHandlerClass);
        return newInstance(constructor, attributeName);
    }

}
