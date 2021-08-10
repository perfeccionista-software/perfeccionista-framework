package io.perfeccionista.framework.pagefactory.operation.type;

import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.measurements.Point2D;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;

import static io.perfeccionista.framework.invocation.runner.InvocationInfo.actionInvocation;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.DRAG_AND_DROP_METHOD;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.getDeclaredConstructor;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.newInstance;

public class WebDragAndDropOperationType implements WebElementOperationType<Void> {

    private final WebChildElement element;
    private final Point2D target;

    private final InvocationInfo invocationInfo;

    private WebDragAndDropOperationType(WebChildElement element, Point2D target) {
        this.element = element;
        this.target = target;
        String elementName = element.getElementIdentifier().getLastUsedName();
        this.invocationInfo = actionInvocation(DRAG_AND_DROP_METHOD, elementName, target.toString());
    }

    public static WebDragAndDropOperationType of(@NotNull WebChildElement element, @NotNull Point2D target) {
        return new WebDragAndDropOperationType(element, target);
    }

    @Override
    public @NotNull InvocationInfo getInvocationName() {
        return this.invocationInfo;
    }

    @Override
    public @NotNull EndpointHandler<Void> getEndpointHandler() {
        Class<? extends EndpointHandler<Void>> endpointHandlerClass = element.getEndpointHandler(DRAG_AND_DROP_METHOD, Void.class);
        Constructor<? extends EndpointHandler<Void>> constructor = getDeclaredConstructor(endpointHandlerClass);
        return newInstance(constructor, target);
    }

}
