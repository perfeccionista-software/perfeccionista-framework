package io.perfeccionista.framework.pagefactory.operation.type;

import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElementBase;
import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;

import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.DOUBLE_TAP_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.TAP;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.getDeclaredConstructor;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.newInstance;

public class MobileDoubleTapOperationType implements MobileElementOperationType<Void> {

    private final MobileChildElementBase element;

    private MobileDoubleTapOperationType(MobileChildElementBase element) {
        this.element = element;
    }

    public static MobileDoubleTapOperationType of(@NotNull MobileChildElementBase element) {
        return new MobileDoubleTapOperationType(element);
    }

    @Override
    public @NotNull InvocationName getInvocationName() {
        return InvocationName.getterInvocation(DOUBLE_TAP_METHOD, element, TAP);
    }

    @Override
    public @NotNull EndpointHandler<Void> getEndpointHandler() {
        Class<? extends EndpointHandler<Void>> endpointHandlerClass = element.getEndpointHandler(DOUBLE_TAP_METHOD, Void.class);
        Constructor<? extends EndpointHandler<Void>> constructor = getDeclaredConstructor(endpointHandlerClass);
        return newInstance(constructor, element);
    }

}
