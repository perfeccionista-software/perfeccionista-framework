package io.perfeccionista.framework.pagefactory.operation.type;

import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElementBase;
import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;

import static io.perfeccionista.framework.invocation.runner.InvocationName.getterInvocation;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.GET_STRING_ATTRIBUTE_VALUE_METHOD;
import static org.junit.platform.commons.util.ReflectionUtils.getDeclaredConstructor;
import static org.junit.platform.commons.util.ReflectionUtils.newInstance;

public class WebGetStringAttributeValueOperationType implements WebElementOperationType<String> {

    private final WebChildElementBase element;
    private final String attributeName;

    private WebGetStringAttributeValueOperationType(WebChildElementBase element, String attributeName) {
        this.element = element;
        this.attributeName = attributeName;
    }

    public static WebGetStringAttributeValueOperationType of(@NotNull WebChildElementBase element, @NotNull String attributeName) {
        return new WebGetStringAttributeValueOperationType(element, attributeName);
    }

    @Override
    public @NotNull InvocationName getInvocationName() {
        return getterInvocation(GET_STRING_ATTRIBUTE_VALUE_METHOD, element, attributeName);
    }

    @Override
    public @NotNull EndpointHandler<String> getEndpointHandler() {
        Class<? extends EndpointHandler<String>> endpointHandlerClass = element.getEndpointHandler(GET_STRING_ATTRIBUTE_VALUE_METHOD, String.class);
        Constructor<? extends EndpointHandler<String>> constructor = getDeclaredConstructor(endpointHandlerClass);
        return newInstance(constructor, attributeName);
    }

}
