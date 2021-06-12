package io.perfeccionista.framework.pagefactory.operation.type;

import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileGetTextAvailable;
import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;

import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.GET_TEXT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.TEXT;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.getDeclaredConstructor;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.newInstance;

public class MobileGetTextOperationType implements MobileElementOperationType<String> {

    private final MobileGetTextAvailable element;

    private MobileGetTextOperationType(MobileGetTextAvailable element) {
        this.element = element;
    }

    public static MobileGetTextOperationType of(@NotNull MobileGetTextAvailable element) {
        return new MobileGetTextOperationType(element);
    }

    @Override
    public @NotNull InvocationName getInvocationName() {
        return InvocationName.getterInvocation(GET_TEXT_METHOD, element, TEXT);
    }

    @Override
    public @NotNull EndpointHandler<String> getEndpointHandler() {
        Class<? extends EndpointHandler<String>> endpointHandlerClass = element.getEndpointHandler(GET_TEXT_METHOD, String.class);
        Constructor<? extends EndpointHandler<String>> constructor = getDeclaredConstructor(endpointHandlerClass);
        return newInstance(constructor, element);
    }

}
