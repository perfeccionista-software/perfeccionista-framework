package io.perfeccionista.framework.pagefactory.operation.type;

import io.perfeccionista.framework.color.Color;
import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElementBase;
import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;

import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.GET_COLOR_METHOD;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.getDeclaredConstructor;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.newInstance;

public class MobileGetColorOperationType implements MobileElementOperationType<Color> {

    private final MobileChildElementBase element;
    private final String componentName;
    private final String property;

    private MobileGetColorOperationType(MobileChildElementBase element, String componentName, String property) {
        this.element = element;
        this.componentName = componentName;
        this.property = property;
    }

    public static MobileGetColorOperationType of(@NotNull MobileChildElementBase element, @NotNull String componentName, @NotNull String cssProperty) {
        return new MobileGetColorOperationType(element, componentName, cssProperty);
    }

    @Override
    public @NotNull InvocationInfo getInvocationName() {
        return InvocationInfo.getterInvocation(GET_COLOR_METHOD, element, componentName, property);
    }

    @Override
    public @NotNull EndpointHandler<Color> getEndpointHandler() {
        Class<? extends EndpointHandler<Color>> endpointHandlerClass = element.getEndpointHandler(GET_COLOR_METHOD, Color.class);
        Constructor<? extends EndpointHandler<Color>> constructor = getDeclaredConstructor(endpointHandlerClass);
        return newInstance(constructor, element, property);
    }

}
