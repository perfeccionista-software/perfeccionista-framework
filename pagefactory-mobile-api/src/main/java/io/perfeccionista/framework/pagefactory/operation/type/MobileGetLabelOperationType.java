package io.perfeccionista.framework.pagefactory.operation.type;

import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElementBase;
import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;

import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.GET_LABEL_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.LABEL;
import static org.junit.platform.commons.util.ReflectionUtils.getDeclaredConstructor;
import static org.junit.platform.commons.util.ReflectionUtils.newInstance;

public class MobileGetLabelOperationType implements MobileElementOperationType<String> {

    private final MobileChildElementBase element;

    private MobileGetLabelOperationType(MobileChildElementBase element) {
        this.element = element;
    }

    public static MobileGetLabelOperationType of(@NotNull MobileChildElementBase element) {
        return new MobileGetLabelOperationType(element);
    }

    @Override
    public @NotNull InvocationName getInvocationName() {
        return InvocationName.getterInvocation(GET_LABEL_METHOD, element, LABEL);
    }

    @Override
    public @NotNull EndpointHandler<String> getEndpointHandler() {
        Class<? extends EndpointHandler<String>> endpointHandlerClass = element.getEndpointHandler(GET_LABEL_METHOD, String.class);
        Constructor<? extends EndpointHandler<String>> constructor = getDeclaredConstructor(endpointHandlerClass);
        return newInstance(constructor, element);
    }

}
