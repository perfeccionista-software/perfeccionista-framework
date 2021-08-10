package io.perfeccionista.framework.pagefactory.operation.type;

import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElementBase;
import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

import static io.perfeccionista.framework.invocation.runner.InvocationInfo.actionInvocation;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.EXECUTE_ACTION;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.getDeclaredConstructor;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.newInstance;

public class WebActionOperationType implements WebElementOperationType<Void> {

    private final String endpointHandlerName;
    private final WebChildElementBase element;
    private final Object[] args;

    private final InvocationInfo invocationInfo;

    private WebActionOperationType(WebChildElementBase element, String endpointHandlerName, Object... args) {
        this.element = element;
        this.endpointHandlerName = endpointHandlerName;
        this.args = args;
        String elementName = element.getElementIdentifier().getLastUsedName();
        String argsAsString = Arrays.stream(args).map(Object::toString).collect(Collectors.joining("; "));
        this.invocationInfo = actionInvocation(EXECUTE_ACTION, elementName, endpointHandlerName, argsAsString);
    }

    public static WebActionOperationType of(@NotNull WebChildElementBase element,
                                            @NotNull String invocationName,
                                            @NotNull Object... args) {
        return new WebActionOperationType(element, invocationName, args);
    }

    @Override
    public @NotNull InvocationInfo getInvocationName() {
        return invocationInfo;
    }

    @Override
    public @NotNull EndpointHandler<Void> getEndpointHandler() {
        Class<? extends EndpointHandler<Void>> endpointHandlerClass = element.getEndpointHandler(endpointHandlerName, Void.class);
        Constructor<? extends EndpointHandler<Void>> constructor = getDeclaredConstructor(endpointHandlerClass);
        return (Objects.isNull(args) || args.length == 0) ? newInstance(constructor) : newInstance(constructor, args);
    }

}
