package io.perfeccionista.framework.pagefactory.operation.type;

import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.pagefactory.elements.methods.WebIsSelectedAvailable;
import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;

import static io.perfeccionista.framework.invocation.runner.InvocationInfo.getterInvocation;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.IS_SELECTED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.SELECTED;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.getDeclaredConstructor;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.newInstance;

public class WebGetIsSelectedOperationType implements WebElementOperationType<Boolean> {

    private final WebIsSelectedAvailable element;

    private WebGetIsSelectedOperationType(WebIsSelectedAvailable element) {
        this.element = element;
    }

    public static WebGetIsSelectedOperationType of(@NotNull WebIsSelectedAvailable element) {
        return new WebGetIsSelectedOperationType(element);
    }

    @Override
    public @NotNull InvocationInfo getInvocationName() {
        return getterInvocation(IS_SELECTED_METHOD, element, SELECTED);
    }

    @Override
    public @NotNull EndpointHandler<Boolean> getEndpointHandler() {
        Class<? extends EndpointHandler<Boolean>> endpointHandlerClass = element.getEndpointHandler(IS_SELECTED_METHOD, Boolean.class);
        Constructor<? extends EndpointHandler<Boolean>> constructor = getDeclaredConstructor(endpointHandlerClass);
        return newInstance(constructor);
    }

}
