package io.perfeccionista.framework.pagefactory.operation.type;

import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElementBase;
import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;
import io.perfeccionista.framework.screenshots.Screenshot;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;

import static io.perfeccionista.framework.invocation.runner.InvocationInfo.getterInvocation;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.GET_SCREENSHOT_METHOD;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.getDeclaredConstructor;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.newInstance;

public class MobileGetScreenshotOperationType implements MobileElementOperationType<Screenshot> {

    private final MobileChildElementBase element;

    private final InvocationInfo invocationInfo;

    private MobileGetScreenshotOperationType(@NotNull MobileChildElementBase element, String componentName) {
        this.element = element;
        var elementName = element.getElementIdentifier().getLastUsedName();
        this.invocationInfo = getterInvocation(GET_SCREENSHOT_METHOD, elementName, componentName);
    }

    public static MobileGetScreenshotOperationType of(@NotNull MobileChildElementBase element, @NotNull String componentName) {
        return new MobileGetScreenshotOperationType(element, componentName);
    }

    @Override
    public @NotNull InvocationInfo getInvocationName() {
        return this.invocationInfo;
    }

    @Override
    public @NotNull EndpointHandler<Screenshot> getEndpointHandler() {
        Class<? extends EndpointHandler<Screenshot>> endpointHandlerClass = element.getEndpointHandler(GET_SCREENSHOT_METHOD, Screenshot.class);
        Constructor<? extends EndpointHandler<Screenshot>> constructor = getDeclaredConstructor(endpointHandlerClass);
        return newInstance(constructor, element);
    }

}
