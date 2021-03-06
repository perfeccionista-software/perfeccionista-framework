package io.perfeccionista.framework.pagefactory.operation.type;

import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElementBase;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;

import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.CLEAR_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.CLEAR;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.getDeclaredConstructor;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.newInstance;

// Создаем со всеми аргументами
public class MobileClearOperationType implements MobileElementOperationType<Void> {

    private final MobileChildElementBase element;

    private MobileClearOperationType(MobileChildElementBase element) {
        this.element = element;
    }

    public static MobileClearOperationType of(@NotNull MobileChildElementBase element) {
        return new MobileClearOperationType(element);
    }

    @Override
    public @NotNull InvocationInfo getInvocationName() {
        return InvocationInfo.getterInvocation(CLEAR_METHOD, element, CLEAR);
    }

    @Override
    public @NotNull EndpointHandler<Void> getEndpointHandler() {
        Class<? extends EndpointHandler<Void>> endpointHandlerClass = element.getEndpointHandler(CLEAR_METHOD, Void.class);
        Constructor<? extends EndpointHandler<Void>> constructor = getDeclaredConstructor(endpointHandlerClass);
        return newInstance(constructor, element);
    }

}
