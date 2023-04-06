package io.perfeccionista.framework.pagefactory.operation.type;

import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.pagefactory.elements.WebNode;
import io.perfeccionista.framework.pagefactory.elements.options.DragAndDropOptions;
import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;

import static io.perfeccionista.framework.invocation.runner.InvocationInfo.actionInvocation;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.DRAG_AND_DROP_METHOD;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.getDeclaredConstructor;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.newInstance;

public class WebDragAndDropOperationType implements WebElementOperationType<Void> {

    private static final String ACTION_NAME = DRAG_AND_DROP_METHOD;

    private final WebNode element;
    private final WebNode target;
    private final DragAndDropOptions options;

    private final InvocationInfo invocationInfo;

    private WebDragAndDropOperationType(WebNode element, WebNode target, DragAndDropOptions options) {
        this.element = element;
        this.target = target;
        this.options = options;
        String elementName = element.getElementIdentifier().getLastUsedName();
        String targetName = target.getElementIdentifier().getLastUsedName();
        this.invocationInfo = actionInvocation(ACTION_NAME, elementName, targetName, options.toString());
    }

    public static WebDragAndDropOperationType of(@NotNull WebNode element, @NotNull WebNode target, @NotNull DragAndDropOptions options) {
        return new WebDragAndDropOperationType(element, target, options);
    }

    @Override
    public @NotNull InvocationInfo getInvocationName() {
        return this.invocationInfo;
    }

    @Override
    public @NotNull EndpointHandler<Void> getEndpointHandler() {
        Class<? extends EndpointHandler<Void>> endpointHandlerClass = element.getEndpointHandler(ACTION_NAME, Void.class);
        Constructor<? extends EndpointHandler<Void>> constructor = getDeclaredConstructor(endpointHandlerClass);

        // TODO: Поправить хэндлер
        return newInstance(constructor, target, options);
    }

}
