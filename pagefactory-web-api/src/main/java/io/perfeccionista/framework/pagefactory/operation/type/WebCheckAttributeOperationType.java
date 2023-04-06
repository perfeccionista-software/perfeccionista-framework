package io.perfeccionista.framework.pagefactory.operation.type;

import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElementBase;
import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;

import static io.perfeccionista.framework.invocation.runner.InvocationInfo.getterInvocation;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.CHECK_ATTRIBUTE_METHOD;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.getDeclaredConstructor;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.newInstance;

public class WebCheckAttributeOperationType implements WebElementOperationType<Boolean> {

    private static final String ACTION_NAME = CHECK_ATTRIBUTE_METHOD;

    private final WebChildElementBase element;
    private final String attributeName;

    private final InvocationInfo invocationInfo;

    private WebCheckAttributeOperationType(WebChildElementBase element, String componentName, String attributeName) {
        this.element = element;
        this.attributeName = attributeName;
        String elementName = element.getElementIdentifier().getLastUsedName();
        this.invocationInfo = getterInvocation(ACTION_NAME, elementName, componentName, attributeName);
    }

    public static WebCheckAttributeOperationType of(@NotNull WebChildElementBase element,
                                                    @NotNull String componentName,
                                                    @NotNull String attributeName) {
        return new WebCheckAttributeOperationType(element, componentName, attributeName);
    }

    @Override
    public @NotNull InvocationInfo getInvocationName() {
        return this.invocationInfo;
    }

    @Override
    public @NotNull EndpointHandler<Boolean> getEndpointHandler() {
        Class<? extends EndpointHandler<Boolean>> endpointHandlerClass = element.getEndpointHandler(ACTION_NAME, Boolean.class);
        Constructor<? extends EndpointHandler<Boolean>> constructor = getDeclaredConstructor(endpointHandlerClass);
        return newInstance(constructor, attributeName);
    }

}
