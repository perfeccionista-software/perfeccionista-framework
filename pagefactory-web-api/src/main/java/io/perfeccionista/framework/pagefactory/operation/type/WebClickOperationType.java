package io.perfeccionista.framework.pagefactory.operation.type;

import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.pagefactory.elements.methods.WebClickAvailable;
import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;

import static io.perfeccionista.framework.invocation.runner.InvocationName.actionInvocation;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.CLICK_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.CLICK;
import static org.junit.platform.commons.util.ReflectionUtils.getDeclaredConstructor;
import static org.junit.platform.commons.util.ReflectionUtils.newInstance;

public class WebClickOperationType implements WebElementOperationType<Void> {

    private final WebClickAvailable element;

    private WebClickOperationType(WebClickAvailable element) {
        this.element = element;
    }

    public static WebClickOperationType of(@NotNull WebClickAvailable element) {
        return new WebClickOperationType(element);
    }

    @Override
    public @NotNull InvocationName getInvocationName() {
        return actionInvocation(CLICK_METHOD, element, CLICK);
    }

    @Override
    public @NotNull EndpointHandler<Void> getEndpointHandler() {
        Class<? extends EndpointHandler<Void>> endpointHandlerClass = element.getEndpointHandler(CLICK_METHOD, Void.class);
        Constructor<? extends EndpointHandler<Void>> constructor = getDeclaredConstructor(endpointHandlerClass);
        return newInstance(constructor);
    }

}
