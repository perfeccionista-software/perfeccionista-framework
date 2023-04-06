package io.perfeccionista.framework.pagefactory.operation.type;

import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.pagefactory.elements.methods.WebIsOnTheScreenAvailable;
import io.perfeccionista.framework.pagefactory.elements.options.OnTheScreenOptions;
import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;

import static io.perfeccionista.framework.invocation.runner.InvocationInfo.getterInvocation;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.IS_ON_THE_SCREEN_METHOD;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.getDeclaredConstructor;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.newInstance;

public class WebGetIsOnTheScreenOperationType implements WebElementOperationType<Boolean> {

    private static final String ACTION_NAME = IS_ON_THE_SCREEN_METHOD;

    private final WebIsOnTheScreenAvailable element;
    private final OnTheScreenOptions options;

    private final InvocationInfo invocationInfo;

    private WebGetIsOnTheScreenOperationType(WebIsOnTheScreenAvailable element, OnTheScreenOptions options) {
        this.element = element;
        this.options = options;
        String elementName = element.getElementIdentifier().getLastUsedName();
        this.invocationInfo = getterInvocation(ACTION_NAME, elementName, options.toString());
    }

    public static WebGetIsOnTheScreenOperationType of(@NotNull WebIsOnTheScreenAvailable element, @NotNull OnTheScreenOptions options) {
        return new WebGetIsOnTheScreenOperationType(element, options);
    }

    @Override
    public @NotNull InvocationInfo getInvocationName() {
        return this.invocationInfo;
    }

    @Override
    public @NotNull EndpointHandler<Boolean> getEndpointHandler() {
        Class<? extends EndpointHandler<Boolean>> endpointHandlerClass = element.getEndpointHandler(ACTION_NAME, Boolean.class);
        Constructor<? extends EndpointHandler<Boolean>> constructor = getDeclaredConstructor(endpointHandlerClass);
        return newInstance(constructor, element, options);
    }

}
