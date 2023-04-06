package io.perfeccionista.framework.pagefactory.operation.type;

import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElementBase;
import io.perfeccionista.framework.pagefactory.emulator.keys.Key;
import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;

import static io.perfeccionista.framework.invocation.runner.InvocationInfo.actionInvocation;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.PRESS_KEY_METHOD;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.getDeclaredConstructor;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.newInstance;

public class WebPressKeyOperationType implements WebElementOperationType<Void> {

    private static final String ACTION_NAME = PRESS_KEY_METHOD;

    private final WebChildElementBase element;
    private final Key key;

    private final InvocationInfo invocationInfo;

    private WebPressKeyOperationType(WebChildElementBase element, Key key) {
        this.element = element;
        this.key = key;
        String elementName = element.getElementIdentifier().getLastUsedName();
        this.invocationInfo = actionInvocation(ACTION_NAME, elementName, key.toString());
    }

    public static WebPressKeyOperationType of(@NotNull WebChildElementBase element, @NotNull Key key) {
        return new WebPressKeyOperationType(element, key);
    }

    @Override
    public @NotNull InvocationInfo getInvocationName() {
        return this.invocationInfo;
    }

    @Override
    public @NotNull EndpointHandler<Void> getEndpointHandler() {
        Class<? extends EndpointHandler<Void>> endpointHandlerClass = element.getEndpointHandler(ACTION_NAME, Void.class);
        Constructor<? extends EndpointHandler<Void>> constructor = getDeclaredConstructor(endpointHandlerClass);
        return newInstance(constructor, key);
    }

}
