package io.perfeccionista.framework.pagefactory.operation.type;

import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElementBase;
import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;
import io.perfeccionista.framework.value.string.StringValue;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;

import static io.perfeccionista.framework.invocation.runner.InvocationInfo.getterInvocation;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.CHECK_STRING_ATTRIBUTE_VALUE_METHOD;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.getDeclaredConstructor;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.newInstance;

public class MobileCheckStringAttributeValueOperationType implements MobileElementOperationType<Boolean> {

    private final MobileChildElementBase element;
    private final String attributeName;
    private final StringValue expectedValue;

    private final InvocationInfo invocationInfo;

    private MobileCheckStringAttributeValueOperationType(MobileChildElementBase element, String attributeName, StringValue expectedValue) {
        this.element = element;
        this.attributeName = attributeName;
        this.expectedValue = expectedValue;
        String elementName = element.getElementIdentifier().getLastUsedName();
        this.invocationInfo = getterInvocation(CHECK_STRING_ATTRIBUTE_VALUE_METHOD, elementName, attributeName, expectedValue.getShortDescription());
    }

    public static MobileCheckStringAttributeValueOperationType of(@NotNull MobileChildElementBase element,
                                                                  @NotNull String attributeName,
                                                                  @NotNull StringValue expectedValue) {
        return new MobileCheckStringAttributeValueOperationType(element, attributeName, expectedValue);
    }

    @Override
    public @NotNull InvocationInfo getInvocationName() {
        return invocationInfo;
    }

    @Override
    public @NotNull EndpointHandler<Boolean> getEndpointHandler() {
        Class<? extends EndpointHandler<Boolean>> endpointHandlerClass = element.getEndpointHandler(CHECK_STRING_ATTRIBUTE_VALUE_METHOD, Boolean.class);
        Constructor<? extends EndpointHandler<Boolean>> constructor = getDeclaredConstructor(endpointHandlerClass);
        return newInstance(constructor, element, attributeName, expectedValue);
    }

}
