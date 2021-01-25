package io.perfeccionista.framework.pagefactory.operation.type;

import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.pagefactory.elements.methods.WebDropDownAvailable;
import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;

import static io.perfeccionista.framework.invocation.runner.InvocationName.actionInvocation;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.OPEN_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.OPEN;
import static org.junit.platform.commons.util.ReflectionUtils.getDeclaredConstructor;
import static org.junit.platform.commons.util.ReflectionUtils.newInstance;

public class WebOpenOperationType implements WebElementOperationType<Void> {

    private final WebDropDownAvailable element;

    private WebOpenOperationType(WebDropDownAvailable element) {
        this.element = element;
    }

    public static WebOpenOperationType of(@NotNull WebDropDownAvailable element) {
        return new WebOpenOperationType(element);
    }

    @Override
    public @NotNull InvocationName getInvocationName() {
        return actionInvocation(OPEN_METHOD, element, OPEN);
    }

    @Override
    public @NotNull EndpointHandler<Void> getEndpointHandler() {
        Class<? extends EndpointHandler<Void>> endpointHandlerClass = element.getEndpointHandler(OPEN_METHOD, Void.class);
        Constructor<? extends EndpointHandler<Void>> constructor = getDeclaredConstructor(endpointHandlerClass);
        return newInstance(constructor);
    }

}
