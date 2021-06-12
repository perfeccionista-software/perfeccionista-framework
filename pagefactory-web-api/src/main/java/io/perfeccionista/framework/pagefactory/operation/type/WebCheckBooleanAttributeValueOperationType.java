package io.perfeccionista.framework.pagefactory.operation.type;

import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElementBase;
import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;

import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.CHECK_BOOLEAN_ATTRIBUTE_VALUE_METHOD;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.getDeclaredConstructor;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.newInstance;

public class WebCheckBooleanAttributeValueOperationType implements WebElementOperationType<Boolean> {

    private final WebChildElementBase element;
    private final String attributeName;
    private final boolean expectedValue;

    private WebCheckBooleanAttributeValueOperationType(WebChildElementBase element, String attributeName, boolean expectedValue) {
        this.element = element;
        this.attributeName = attributeName;
        this.expectedValue = expectedValue;
    }

    public static WebCheckBooleanAttributeValueOperationType of(@NotNull WebChildElementBase element,
                                                                @NotNull String attributeName,
                                                                boolean expectedValue) {
        return new WebCheckBooleanAttributeValueOperationType(element, attributeName, expectedValue);
    }

    @Override
    public @NotNull InvocationName getInvocationName() {
        return InvocationName.getterInvocation(CHECK_BOOLEAN_ATTRIBUTE_VALUE_METHOD, element, attributeName);
    }

    @Override
    public @NotNull EndpointHandler<Boolean> getEndpointHandler() {
        Class<? extends EndpointHandler<Boolean>> endpointHandlerClass = element.getEndpointHandler(CHECK_BOOLEAN_ATTRIBUTE_VALUE_METHOD, Boolean.class);
        Constructor<? extends EndpointHandler<Boolean>> constructor = getDeclaredConstructor(endpointHandlerClass);
        return newInstance(constructor, element, attributeName, expectedValue);
    }

}
