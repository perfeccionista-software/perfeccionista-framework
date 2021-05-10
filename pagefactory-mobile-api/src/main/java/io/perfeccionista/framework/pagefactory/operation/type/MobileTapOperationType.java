package io.perfeccionista.framework.pagefactory.operation.type;

import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElementBase;
import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;

import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SINGLE_TAP_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.TAP;
import static org.junit.platform.commons.util.ReflectionUtils.getDeclaredConstructor;
import static org.junit.platform.commons.util.ReflectionUtils.newInstance;

public class MobileTapOperationType implements MobileElementOperationType<Void> {

    private final MobileChildElementBase element;

    private MobileTapOperationType(MobileChildElementBase element) {
        this.element = element;
    }

    public static MobileTapOperationType of(@NotNull MobileChildElementBase element) {
        return new MobileTapOperationType(element);
    }

    @Override
    public @NotNull InvocationName getInvocationName() {
        return InvocationName.actionInvocation(SINGLE_TAP_METHOD, element, TAP);
    }

    @Override
    public @NotNull EndpointHandler<Void> getEndpointHandler() {
        Class<? extends EndpointHandler<Void>> endpointHandlerClass = element.getEndpointHandler(SINGLE_TAP_METHOD, Void.class);
        Constructor<? extends EndpointHandler<Void>> constructor = getDeclaredConstructor(endpointHandlerClass);
        return newInstance(constructor, element);
    }

}
