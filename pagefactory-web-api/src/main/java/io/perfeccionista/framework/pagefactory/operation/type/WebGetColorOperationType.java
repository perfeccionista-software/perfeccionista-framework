package io.perfeccionista.framework.pagefactory.operation.type;

import io.perfeccionista.framework.color.Color;
import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.pagefactory.elements.methods.WebGetColorAvailable;
import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;

import static io.perfeccionista.framework.invocation.runner.InvocationInfo.getterInvocation;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.GET_COLOR_METHOD;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.getDeclaredConstructor;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.newInstance;

public class WebGetColorOperationType implements WebElementOperationType<Color> {

    private static final String ACTION_TYPE = GET_COLOR_METHOD;

    private final WebGetColorAvailable element;
    private final String componentName;
    private final String cssProperty;

    private final InvocationInfo invocationInfo;

    private WebGetColorOperationType(WebGetColorAvailable element, String componentName, String cssProperty) {
        this.element = element;
        this.componentName = componentName;
        this.cssProperty = cssProperty;
        String elementName = element.getElementIdentifier().getLastUsedName();
        this.invocationInfo = getterInvocation(ACTION_TYPE, elementName, componentName, cssProperty);
    }

    public static WebGetColorOperationType of(@NotNull WebGetColorAvailable element, @NotNull String componentName, @NotNull String cssProperty) {
        return new WebGetColorOperationType(element, componentName, cssProperty);
    }

    @Override
    public @NotNull InvocationInfo getInvocationName() {
        return this.invocationInfo;
    }

    @Override
    public @NotNull EndpointHandler<Color> getEndpointHandler() {
        Class<? extends EndpointHandler<Color>> endpointHandlerClass = element.getEndpointHandler(ACTION_TYPE, Color.class);
        Constructor<? extends EndpointHandler<Color>> constructor = getDeclaredConstructor(endpointHandlerClass);
        return newInstance(constructor, cssProperty);
    }

}
