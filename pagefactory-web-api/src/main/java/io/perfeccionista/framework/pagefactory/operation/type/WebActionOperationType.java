package io.perfeccionista.framework.pagefactory.operation.type;

import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElementBase;
import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;
import java.util.Objects;

import static io.perfeccionista.framework.invocation.runner.InvocationName.actionInvocation;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.getDeclaredConstructor;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.newInstance;

public class WebActionOperationType implements WebElementOperationType<Void> {

    private final String endpointHandlerName;
    private final WebChildElementBase element;
    private final Object[] args;

    private WebActionOperationType(WebChildElementBase element, String endpointHandlerName, Object... args) {
        this.element = element;
        this.endpointHandlerName = endpointHandlerName;
        this.args = args;
    }

    public static WebActionOperationType of(@NotNull WebChildElementBase element,
                                            @NotNull String endpointHandlerName,
                                            @NotNull Object... args) {
        return new WebActionOperationType(element, endpointHandlerName, args);
    }

    @Override
    public @NotNull InvocationName getInvocationName() {
        return actionInvocation(endpointHandlerName, element, args);
    }

    @Override
    public @NotNull EndpointHandler<Void> getEndpointHandler() {
        Class<? extends EndpointHandler<Void>> endpointHandlerClass = element.getEndpointHandler(endpointHandlerName, Void.class);
        Constructor<? extends EndpointHandler<Void>> constructor = getDeclaredConstructor(endpointHandlerClass);
        return (Objects.isNull(args) || args.length == 0) ? newInstance(constructor) : newInstance(constructor, args);
    }

}
